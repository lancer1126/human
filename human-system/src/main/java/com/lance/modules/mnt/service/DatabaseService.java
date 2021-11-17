package com.lance.modules.mnt.service;

import com.lance.modules.mnt.service.dto.DatabaseQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface DatabaseService {
    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(DatabaseQueryCriteria criteria, Pageable pageable);
}
