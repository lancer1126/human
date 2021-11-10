package com.lance.modules.quartz.service.impl;

import com.lance.modules.quartz.repository.QuartzJobRepository;
import com.lance.modules.quartz.service.QuartzJobService;
import com.lance.modules.system.service.dto.JobQueryCriteria;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service(value = "quartzJobService")
public class QuartzJobServiceImpl implements QuartzJobService {

    private final QuartzJobRepository quartzJobRepository;

    @Override
    public Object queryAll(JobQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(quartzJobRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable));
    }
}
