package com.lance.modules.system.service;

import com.lance.modules.system.service.dto.RoleDto;
import com.lance.modules.system.service.dto.RoleSmallDto;
import com.lance.modules.system.service.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface RoleService {

    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto user);

    List<RoleSmallDto> findByUsersId(Long currentUserId);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    RoleDto findById(long id);
}
