package com.lance.modules.security.rest;

import com.lance.annotation.rest.AnonymousDeleteMapping;
import com.lance.annotation.rest.AnonymousPostMapping;
import com.lance.config.RsaProperties;
import com.lance.exception.BadRequestException;
import com.lance.modules.security.config.bean.LoginProperties;
import com.lance.modules.security.config.bean.SecurityProperties;
import com.lance.modules.security.security.TokenProvider;
import com.lance.modules.security.service.OnlineUserService;
import com.lance.modules.security.service.dto.AuthUserDto;
import com.lance.modules.security.service.dto.JwtUserDto;
import com.lance.utils.RedisUtils;
import com.lance.utils.RsaUtils;
import com.lance.utils.SecurityUtils;
import com.lance.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    private final TokenProvider tokenProvider;
    private final OnlineUserService onlineUserService;
    private final SecurityProperties properties;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Resource
    private final LoginProperties loginProperties;

    @ApiOperation("登录授权")
    @AnonymousPostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解码
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // 查询验证码
        String code = redisUtils.get(authUser.getUuid()).toString();
        redisUtils.del(authUser.getUuid());

        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }

        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();

        // 保存在线用户的信息
        onlineUserService.save(jwtUserDto, token, request);

        // 返回token与用户信息
        Map<String, Object> authInfo = new HashMap<>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};

        if (loginProperties.isSingleLogin()) {
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
        return ResponseEntity.ok(authInfo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("退出登录")
    @AnonymousDeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        onlineUserService.logout(tokenProvider.getToken(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
