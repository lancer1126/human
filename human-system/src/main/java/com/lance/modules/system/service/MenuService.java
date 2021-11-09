package com.lance.modules.system.service;

import com.lance.modules.system.service.dto.MenuDto;

import java.util.List;

public interface MenuService {

    List<MenuDto> findByUser(Long currentUserId);

    /**
     * 构造菜单树
     */
    List<MenuDto> buildTree(List<MenuDto> menuDtoList);

    Object buildMenus(List<MenuDto> menuDtos);
}
