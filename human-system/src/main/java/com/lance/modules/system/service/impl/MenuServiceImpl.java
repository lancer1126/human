package com.lance.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.lance.modules.system.domain.Menu;
import com.lance.modules.system.domain.vo.MenuMetaVo;
import com.lance.modules.system.domain.vo.MenuVo;
import com.lance.modules.system.repository.MenuRepository;
import com.lance.modules.system.service.MenuService;
import com.lance.modules.system.service.RoleService;
import com.lance.modules.system.service.dto.MenuDto;
import com.lance.modules.system.service.dto.MenuQueryCriteria;
import com.lance.modules.system.service.dto.RoleSmallDto;
import com.lance.modules.system.service.mapstruct.MenuMapper;
import com.lance.utils.QueryHelp;
import com.lance.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "menu")
public class MenuServiceImpl implements MenuService {

    private final RoleService roleService;
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @Override
//    @Cacheable(key = "'user:' + #p0")
    public List<MenuDto> findByUser(Long currentUserId) {
        List<RoleSmallDto> roles = roleService.findByUsersId(currentUserId);
        Set<Long> roleIds = roles.stream().map(RoleSmallDto::getId).collect(Collectors.toSet());
        LinkedHashSet<Menu> menus = menuRepository.findByRoleIdsAndTypeNot(roleIds, 2);
        return menus.stream().map(menuMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuDto> buildTree(List<MenuDto> menuDtoList) {
        List<MenuDto> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDto menuDTO : menuDtoList) {
            if (menuDTO.getPid() == null) {
                trees.add(menuDTO);
            }
            for (MenuDto it : menuDtoList) {
                if (menuDTO.getId().equals(it.getPid())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if(trees.size() == 0){
            trees = menuDtoList.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public List<MenuVo> buildMenus(List<MenuDto> menuDtos) {
        List<MenuVo> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO!=null){
                        List<MenuDto> menuDtoList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName())  ? menuDTO.getComponentName() : menuDTO.getTitle());
                        // ????????????????????????????????????????????????
                        menuVo.setPath(menuDTO.getPid() == null ? "/" + menuDTO.getPath() :menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // ??????????????????
                        if(!menuDTO.getIFrame()){
                            if(menuDTO.getPid() == null){
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent())?"Layout":menuDTO.getComponent());
                                // ?????????????????????????????????????????????????????????????????????????????????
                            }else if(menuDTO.getType() == 0){
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent())?"ParentView":menuDTO.getComponent());
                            }else if(StringUtils.isNoneBlank(menuDTO.getComponent())){
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getTitle(),menuDTO.getIcon(),!menuDTO.getCache()));
                        if(CollectionUtil.isNotEmpty(menuDtoList)){
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // ???????????????????????????????????????????????????
                        } else if(menuDTO.getPid() == null){
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // ?????????
                            if(!menuDTO.getIFrame()){
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    @Override
    public List<MenuDto> getMenus(Long pid) {
        List<Menu> menus;
        if(pid != null && !pid.equals(0L)){
            menus = menuRepository.findByPid(pid);
        } else {
            menus = menuRepository.findByPidIsNull();
        }
        return menuMapper.toDto(menus);
    }

    @Override
    public List<MenuDto> queryAll(MenuQueryCriteria criteria, Boolean isQuery) throws Exception {
        Sort sort = Sort.by(Sort.Direction.ASC, "menuSort");
        if(Boolean.TRUE.equals(isQuery)){
            criteria.setPidIsNull(true);
            List<Field> fields = QueryHelp.getAllFields(criteria.getClass(), new ArrayList<>());
            for (Field field : fields) {
                //???????????????????????????????????????private??????????????????
                field.setAccessible(true);
                Object val = field.get(criteria);
                if("pidIsNull".equals(field.getName())){
                    continue;
                }
                if (ObjectUtil.isNotNull(val)) {
                    criteria.setPidIsNull(null);
                    break;
                }
            }
        }
        return menuMapper.toDto(menuRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),sort));
    }
}
