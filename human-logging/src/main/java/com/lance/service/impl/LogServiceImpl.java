package com.lance.service.impl;

import com.lance.domain.Log;
import com.lance.repository.LogRepository;
import com.lance.service.LogService;
import com.lance.service.dto.LogQueryCriteria;
import com.lance.service.mapstruct.LogErrorMapper;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogErrorMapper logErrorMapper;

    @Override
    public Object queryAll(LogQueryCriteria criteria, Pageable pageable) {
        Page<Log> page = logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        String status = "ERROR";
        if (status.equals(criteria.getLogType())) {
            return PageUtil.toPage(page.map(logErrorMapper::toDto));
        }
        return page;
    }
}
