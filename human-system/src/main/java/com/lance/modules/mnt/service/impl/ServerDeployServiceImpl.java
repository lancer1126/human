package com.lance.modules.mnt.service.impl;

import com.lance.modules.mnt.domain.ServerDeploy;
import com.lance.modules.mnt.repository.ServerDeployRepository;
import com.lance.modules.mnt.service.ServerDeployService;
import com.lance.modules.mnt.service.dto.ServerDeployQueryCriteria;
import com.lance.modules.mnt.service.mapstruct.ServerDeployMapper;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServerDeployServiceImpl implements ServerDeployService {

    private final ServerDeployRepository serverDeployRepository;
    private final ServerDeployMapper serverDeployMapper;

    @Override
    public Object queryAll(ServerDeployQueryCriteria criteria, Pageable pageable) {
        Page<ServerDeploy> page = serverDeployRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(serverDeployMapper::toDto));
    }
}
