package com.lance.modules.system.service;

import com.lance.modules.system.service.dto.RoleDto;
import com.lance.modules.system.service.dto.RoleQueryCriteria;
import com.lance.modules.system.service.dto.RoleSmallDto;
import com.lance.modules.system.service.dto.UserDto;
import org.springframework.data.domain.Pageable;
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

    /**
     * 待条件分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(RoleQueryCriteria criteria, Pageable pageable);
}
