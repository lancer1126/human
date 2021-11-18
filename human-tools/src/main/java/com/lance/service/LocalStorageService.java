package com.lance.service;

import com.lance.service.dto.LocalStorageQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface LocalStorageService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(LocalStorageQueryCriteria criteria, Pageable pageable);
}
