package com.lance.modules.system.service.impl;

import com.lance.modules.system.domain.Dict;
import com.lance.modules.system.repository.DictRepository;
import com.lance.modules.system.service.DictService;
import com.lance.modules.system.service.dto.DictDto;
import com.lance.modules.system.service.dto.DictQueryCriteria;
import com.lance.modules.system.service.mapstruct.DictMapper;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dict")
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository;
    private final DictMapper dictMapper;

    @Override
    public List<DictDto> queryAll(DictQueryCriteria dict) {
        List<Dict> list = dictRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, dict, cb));
        return dictMapper.toDto(list);
    }
}
