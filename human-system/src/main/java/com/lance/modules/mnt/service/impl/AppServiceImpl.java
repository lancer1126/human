package com.lance.modules.mnt.service.impl;

import com.lance.modules.mnt.domain.App;
import com.lance.modules.mnt.repository.AppRepository;
import com.lance.modules.mnt.service.AppService;
import com.lance.modules.mnt.service.dto.AppQueryCriteria;
import com.lance.modules.mnt.service.mapstruct.AppMapper;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final AppRepository appRepository;
    private final AppMapper appMapper;

    @Override
    public Object queryAll(AppQueryCriteria criteria, Pageable pageable) {
        Page<App> page = appRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(appMapper::toDto));
    }
}
