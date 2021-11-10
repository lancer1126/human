package com.lance.modules.system.service;

import com.lance.modules.system.service.dto.MenuDto;
import com.lance.modules.system.service.dto.MenuQueryCriteria;

import java.util.List;

public interface MenuService {

    List<MenuDto> findByUser(Long currentUserId);

    /**
     * 构造菜单树
     */
    List<MenuDto> buildTree(List<MenuDto> menuDtoList);

    Object buildMenus(List<MenuDto> menuDtos);

    /**
     * 懒加载菜单数据
     * @param pid /
     * @return /
     */
    List<MenuDto> getMenus(Long pid);

    /**
     * 查询全部数据
     * @param criteria 条件
     * @param isQuery /
     * @throws Exception /
     * @return /
     */
    List<MenuDto> queryAll(MenuQueryCriteria criteria, Boolean isQuery) throws Exception;
}
