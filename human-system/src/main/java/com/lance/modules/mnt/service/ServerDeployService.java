package com.lance.modules.mnt.service;

import com.lance.modules.mnt.service.dto.ServerDeployQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface ServerDeployService {
    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(ServerDeployQueryCriteria criteria, Pageable pageable);
}
