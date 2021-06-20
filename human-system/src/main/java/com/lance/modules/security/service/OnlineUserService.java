package com.lance.modules.security.service;

import com.lance.modules.security.config.bean.SecurityProperties;
import com.lance.modules.security.service.dto.JwtUserDto;
import com.lance.modules.security.service.dto.OnlineUserDto;
import com.lance.utils.EncryptUtils;
import com.lance.utils.RedisUtils;
import com.lance.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lancer1126
 */
@Slf4j
@Service
@AllArgsConstructor
public class OnlineUserService {

    private final RedisUtils redisUtils;
    private final SecurityProperties properties;

    /**
     * 保存在线用户信息
     * @param jwtUserDto    jwtUserDto
     * @param token         token
     * @param request       request
     */
    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request) {
        String dept = jwtUserDto.getUser().getDept().getName();
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;

        try {
            onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(), jwtUserDto.getUser().getNickName(),
                    dept, browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDto, properties.getTokenValidityInSeconds()/1000);
    }
}
