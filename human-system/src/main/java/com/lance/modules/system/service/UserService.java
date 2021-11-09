package com.lance.modules.system.service;

import com.lance.modules.system.service.dto.UserDto;
import com.lance.modules.system.service.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface UserService {

    /**
     * 根据用户名查询
     * @param userName /
     * @return /
     */
    UserDto findByName(String userName);

    /**
     * 查询全部
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(UserQueryCriteria criteria, Pageable pageable);

}
