package com.lance.modules.security.security;

import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.JwtBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * token生成
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private JwtBuilder jwtBuilder;
    public static final String AUTHORITIES_KEY = "user";

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
}
