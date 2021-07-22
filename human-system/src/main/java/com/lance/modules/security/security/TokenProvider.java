package com.lance.modules.security.security;

import cn.hutool.core.util.IdUtil;
import com.lance.modules.security.config.bean.SecurityProperties;
import com.lance.utils.RedisUtils;
import io.jsonwebtoken.JwtBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * token生成
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private JwtBuilder jwtBuilder;
    public static final String AUTHORITIES_KEY = "user";

    public TokenProvider(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public String createToken(Authentication authentication) {
        return jwtBuilder
                .setId(IdUtil.simpleUUID())
                .claim(AUTHORITIES_KEY, authentication.getName())
                .setSubject(authentication.getName())
                .compact();
    }

    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }
}
