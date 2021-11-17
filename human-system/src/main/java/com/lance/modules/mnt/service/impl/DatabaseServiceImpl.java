package com.lance.modules.mnt.service.impl;

import com.lance.modules.mnt.domain.Database;
import com.lance.modules.mnt.repository.DatabaseRepository;
import com.lance.modules.mnt.service.DatabaseService;
import com.lance.modules.mnt.service.dto.DatabaseQueryCriteria;
import com.lance.modules.mnt.service.mapstruct.DatabaseMapper;
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
public class DatabaseServiceImpl implements DatabaseService {

    private final DatabaseRepository databaseRepository;
    private final DatabaseMapper databaseMapper;

    @Override
    public Object queryAll(DatabaseQueryCriteria criteria, Pageable pageable){
        Page<Database> page = databaseRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(databaseMapper::toDto));
    }
}
