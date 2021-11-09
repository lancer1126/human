package com.lance.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lance.modules.system.domain.Dept;
import com.lance.modules.system.repository.DeptRepository;
import com.lance.modules.system.service.DeptService;
import com.lance.modules.system.service.dto.DeptDto;
import com.lance.modules.system.service.dto.DeptQueryCriteria;
import com.lance.modules.system.service.mapstruct.DeptMapper;
import com.lance.utils.QueryHelp;
import com.lance.utils.SecurityUtils;
import com.lance.utils.StringUtils;
import com.lance.utils.enums.DataScopeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dept")
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;
    private final DeptMapper deptMapper;

    @Override
    public List<Dept> findByPid(long pid) {
        return deptRepository.findByPid(pid);
    }

    @Override
    public List<Long> getDeptChildren(List<Dept> deptList) {
        List<Long> list = new ArrayList<>();
        deptList.forEach(dept-> {
            if (dept != null && dept.getEnabled()) {
                List<Dept> depts = deptRepository.findByPid(dept.getId());
                if (depts.size() != 0) {
                    list.addAll(getDeptChildren(depts));
                }
                list.add(dept.getId());
            }
        });
        return list;
    }

    @Override
    public Set<Dept> findByRoleId(Long id) {
        return deptRepository.findByRoleId(id);
    }

    @Override
    public List<DeptDto> queryAll(DeptQueryCriteria criteria, Boolean isQuery) throws Exception {
        Sort sort = Sort.by(Sort.Direction.ASC, "deptSort");
        String dataScopeType = SecurityUtils.getDataScopeType();
        if (isQuery) {
            if(dataScopeType.equals(DataScopeEnum.ALL.getValue())){
                criteria.setPidIsNull(true);
            }
            List<Field> fields = QueryHelp.getAllFields(criteria.getClass(), new ArrayList<>());
            List<String> fieldNames = new ArrayList<>() {{
                add("pidIsNull");
                add("enabled");
            }};
            for (Field field : fields) {
                //设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
                Object val = field.get(criteria);
                if(fieldNames.contains(field.getName())){
                    continue;
                }
                if (ObjectUtil.isNotNull(val)) {
                    criteria.setPidIsNull(null);
                    break;
                }
            }
        }
        List<DeptDto> list = deptMapper.toDto(deptRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),sort));
        // 如果为空，就代表为自定义权限或者本级权限，就需要去重，不理解可以注释掉，看查询结果
        if(StringUtils.isBlank(dataScopeType)){
            return deduplication(list);
        }
        return list;
    }

    private List<DeptDto> deduplication(List<DeptDto> list) {
        List<DeptDto> deptDtos = new ArrayList<>();
        for (DeptDto deptDto : list) {
            boolean flag = true;
            for (DeptDto dto : list) {
                if (dto.getId().equals(deptDto.getPid())) {
                    flag = false;
                    break;
                }
            }
            if (flag){
                deptDtos.add(deptDto);
            }
        }
        return deptDtos;
    }
}
