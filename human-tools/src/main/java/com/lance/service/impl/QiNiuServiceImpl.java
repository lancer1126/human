package com.lance.service.impl;

import com.lance.repository.QiniuContentRepository;
import com.lance.service.QiNiuService;
import com.lance.service.dto.QiniuQueryCriteria;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "qiNiu")
public class QiNiuServiceImpl implements QiNiuService {

    private final QiniuContentRepository qiniuContentRepository;

    @Override
    public Object queryAll(QiniuQueryCriteria criteria, Pageable pageable) {
        return PageUtil.toPage(qiniuContentRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable));
    }
}
