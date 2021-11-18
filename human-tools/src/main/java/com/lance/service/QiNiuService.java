package com.lance.service;

import com.lance.service.dto.QiniuQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface QiNiuService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(QiniuQueryCriteria criteria, Pageable pageable);
}
