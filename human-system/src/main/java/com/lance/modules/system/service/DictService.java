package com.lance.modules.system.service;

import com.lance.modules.system.service.dto.DictDto;
import com.lance.modules.system.service.dto.DictQueryCriteria;

import java.util.List;

public interface DictService {

    /**
     * 查询全部数据
     * @param dict /
     * @return /
     */
    List<DictDto> queryAll(DictQueryCriteria dict);
}
