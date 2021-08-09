package com.lance.modules.security.service;

import com.lance.utils.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 用于清理用户登录信息缓存
 */
@Component
public class UserCacheClean {

    public void cleanUserCache(String username) {
        if (StringUtils.isNotEmpty(username)) {
            UserDetailsServiceImpl.userDtoCache.remove(username);
        }
    }
}
