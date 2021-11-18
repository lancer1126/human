package com.lance.service.impl;

import com.lance.domain.LocalStorage;
import com.lance.repository.LocalStorageRepository;
import com.lance.service.LocalStorageService;
import com.lance.service.dto.LocalStorageQueryCriteria;
import com.lance.service.mapstruct.LocalStorageMapper;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalStorageServiceImpl implements LocalStorageService {

    private final LocalStorageRepository localStorageRepository;
    private final LocalStorageMapper localStorageMapper;

    @Override
    public Object queryAll(LocalStorageQueryCriteria criteria, Pageable pageable) {
        Page<LocalStorage> page = localStorageRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(localStorageMapper::toDto));
    }
}
