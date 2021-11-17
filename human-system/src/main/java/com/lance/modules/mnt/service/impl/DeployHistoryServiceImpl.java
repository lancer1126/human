package com.lance.modules.mnt.service.impl;

import com.lance.modules.mnt.domain.DeployHistory;
import com.lance.modules.mnt.repository.DeployHistoryRepository;
import com.lance.modules.mnt.service.DeployHistoryService;
import com.lance.modules.mnt.service.dto.DeployHistoryQueryCriteria;
import com.lance.modules.mnt.service.mapstruct.DeployHistoryMapper;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeployHistoryServiceImpl implements DeployHistoryService {

    private final DeployHistoryRepository deployhistoryRepository;
    private final DeployHistoryMapper deployhistoryMapper;

    @Override
    public Object queryAll(DeployHistoryQueryCriteria criteria, Pageable pageable) {
        Page<DeployHistory> page = deployhistoryRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(deployhistoryMapper::toDto));
    }
}
