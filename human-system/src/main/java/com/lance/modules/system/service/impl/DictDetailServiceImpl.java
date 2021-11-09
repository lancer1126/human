package com.lance.modules.system.service.impl;

import com.lance.modules.system.domain.DictDetail;
import com.lance.modules.system.repository.DictDetailRepository;
import com.lance.modules.system.service.DictDetailService;
import com.lance.modules.system.service.dto.DictDetailQueryCriteria;
import com.lance.modules.system.service.mapstruct.DictDetailMapper;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dict")
public class DictDetailServiceImpl implements DictDetailService {

    private final DictDetailRepository dictDetailRepository;
    private final DictDetailMapper dictDetailMapper;

    @Override
    public Map<String, Object> queryAll(DictDetailQueryCriteria criteria, Pageable pageable) {
        Page<DictDetail> page = dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(dictDetailMapper::toDto));
    }
}
