package com.lance.modules.system.service.impl;

import com.lance.modules.system.domain.Job;
import com.lance.modules.system.repository.JobRepository;
import com.lance.modules.system.service.JobService;
import com.lance.modules.system.service.dto.JobQueryCriteria;
import com.lance.modules.system.service.mapstruct.JobMapper;
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
@CacheConfig(cacheNames = "job")
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public Map<String,Object> queryAll(JobQueryCriteria criteria, Pageable pageable) {
        Page<Job> page = jobRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(jobMapper::toDto).getContent(),page.getTotalElements());
    }
}
