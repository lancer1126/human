package com.lance.modules.security.service;

import cn.hutool.core.util.PageUtil;
import com.lance.modules.security.config.bean.SecurityProperties;
import com.lance.modules.security.service.dto.JwtUserDto;
import com.lance.modules.security.service.dto.OnlineUserDto;
import com.lance.utils.EncryptUtils;
import com.lance.utils.RedisUtils;
import com.lance.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author lancer1126
 */
@Slf4j
@Service
@RequiredArgsConstructor
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
            log.error(e.getMessage(), e);
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDto, properties.getTokenValidityInSeconds()/1000);
    }

    /**
     * 不分页查询全部数据
     * @param filter    查询条件
     * @return          /
     */
    public List<OnlineUserDto> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);

        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            OnlineUserDto onlineUserDto = (OnlineUserDto)redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUserDto.toString().contains(filter)) {
                    onlineUserDtos.add(onlineUserDto);
                }
            } else {
                onlineUserDtos.add(onlineUserDto);
            }
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }

    /**
     * 踢出用户
     * @param key /
     */
    public void kickOut(String key){
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }

    /**
     * 检测该账号是否已经登录，若已登录则将之踢下线
     * @param userName      userName
     * @param ignoreToken   现登录的token，由这个token进行登录
     */
    public void checkLoginOnUser(String userName, String ignoreToken) {
        List<OnlineUserDto> onlineUserDtos = getAll(userName);
        if (onlineUserDtos == null || onlineUserDtos.isEmpty()) {
            return;
        }

        for (OnlineUserDto onlineUserDto : onlineUserDtos) {
            if (userName.equals(onlineUserDto.getUserName())) {
                try {
                    String token = EncryptUtils.desEncrypt(onlineUserDto.getKey());
                    if ((StringUtils.isNotBlank(ignoreToken) && !ignoreToken.equals(token))
                            || StringUtils.isBlank(ignoreToken)) {
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is null", e);
                }
            }
        }
    }

    /**
     * 退出登录
     * @param token /
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 查询单个用户
     * @param key /
     */
    public OnlineUserDto getOne(String key) {
        return (OnlineUserDto) redisUtils.get(key);
    }
}
