package com.lance.modules.system.service.impl;

import com.lance.modules.system.service.DataService;
import com.lance.modules.system.service.RoleService;
import com.lance.modules.system.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class DataServiceImpl implements DataService {

    private final RoleService roleService;

    /**
     * 用户角色改变时清理缓存
     * @param user /
     */
    @Override
    public List<Long> getDeptIds(UserDto user) {
        return null;
    }
}
