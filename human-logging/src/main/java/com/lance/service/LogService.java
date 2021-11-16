package com.lance.service;

import com.lance.service.dto.LogQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface LogService {

    /**
     * 分页查询
     * @param criteria 查询条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(LogQueryCriteria criteria, Pageable pageable);
}
