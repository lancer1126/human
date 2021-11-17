package com.lance.modules.mnt.service.impl;

import com.lance.modules.mnt.domain.Deploy;
import com.lance.modules.mnt.repository.DeployRepository;
import com.lance.modules.mnt.service.DeployService;
import com.lance.modules.mnt.service.dto.DeployQueryCriteria;
import com.lance.modules.mnt.service.mapstruct.DeployMapper;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeployServiceImpl implements DeployService {

    private final DeployRepository deployRepository;
    private final DeployMapper deployMapper;

    @Override
    public Object queryAll(DeployQueryCriteria criteria, Pageable pageable) {
        Page<Deploy> page = deployRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(deployMapper::toDto));
    }
}
