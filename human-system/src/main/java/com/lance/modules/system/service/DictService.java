package com.lance.modules.system.service;

import com.lance.modules.system.service.dto.DictDto;
import com.lance.modules.system.service.dto.DictQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface DictService {

    /**
     * 查询全部数据
     * @param dict /
     * @return /
     */
    List<DictDto> queryAll(DictQueryCriteria dict);

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Map<String,Object> queryAll(DictQueryCriteria criteria, Pageable pageable);
}
