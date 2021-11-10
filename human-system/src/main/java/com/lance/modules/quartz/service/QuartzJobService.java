package com.lance.modules.quartz.service;

import com.lance.modules.system.service.dto.JobQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface QuartzJobService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(JobQueryCriteria criteria, Pageable pageable);
}
