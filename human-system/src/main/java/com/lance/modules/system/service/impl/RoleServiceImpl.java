package com.lance.modules.system.service.impl;

import com.lance.modules.system.domain.Menu;
import com.lance.modules.system.domain.Role;
import com.lance.modules.system.repository.RoleRepository;
import com.lance.modules.system.service.RoleService;
import com.lance.modules.system.service.dto.RoleDto;
import com.lance.modules.system.service.dto.RoleSmallDto;
import com.lance.modules.system.service.dto.UserDto;
import com.lance.modules.system.service.mapstruct.RoleMapper;
import com.lance.modules.system.service.mapstruct.RoleSmallMapper;
import com.lance.utils.StringUtils;
import com.lance.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleSmallMapper roleSmallMapper;
    private final RoleMapper roleMapper;

    @Override
    @Cacheable(key = "'auth:' + #p0.id")
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        Set<String> permissions = new HashSet<>();
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        Set<Role> roles = roleRepository.findByUserId(user.getId());
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .map(Menu::getPermission)
                .filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleSmallDto> findByUsersId(Long currentUserId) {
        return roleSmallMapper.toDto(new ArrayList<>(roleRepository.findByUserId(currentUserId)));
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    @Transactional(rollbackFor = Exception.class)
    public RoleDto findById(long id) {
        Role role = roleRepository.findById(id).orElseGet(Role::new);
        ValidationUtil.isNull(role.getId(), "Role", "id", id);
        return roleMapper.toDto(role);
    }
}
