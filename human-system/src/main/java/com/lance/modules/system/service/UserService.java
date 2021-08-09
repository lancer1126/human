package com.lance.modules.system.service;

import com.lance.modules.system.service.dto.UserDto;

public interface UserService {

    /**
     * 根据用户名查询
     * @param userName /
     * @return /
     */
    UserDto findByName(String userName);

}
