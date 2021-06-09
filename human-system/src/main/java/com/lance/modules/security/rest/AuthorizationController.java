package com.lance.modules.security.rest;

import com.lance.annotation.rest.AnonymousPostMapping;
import com.lance.config.RsaProperties;
import com.lance.exception.BadRequestException;
import com.lance.modules.security.service.dto.AuthUserDto;
import com.lance.utils.RedisUtils;
import com.lance.utils.RsaUtils;
import com.lance.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权，根据token获取用户信息
 */
@Api(tags = "系统授权接口")
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final RedisUtils redisUtils;

    @ApiOperation("登录授权")
    @AnonymousPostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解码
        RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // 查询验证码
        String code = redisUtils.get(authUser.getUuid()).toString();
        redisUtils.del(authUser.getUuid());

        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }

        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }
        return null;
    }
}
