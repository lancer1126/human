package com.lance.config;

import com.lance.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "el")
public class HumanPermissionConfig {
    public Boolean check(String ...permissions) {
        // 获取当前用户的所有权限
        List<String> humanPermissions = SecurityUtils.getCurrentUser().getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return humanPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(humanPermissions::contains);
    }
}
