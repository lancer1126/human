package com.lance.modules.mnt.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.mnt.domain.Database;
import com.lance.modules.mnt.service.dto.DatabaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DatabaseMapper extends BaseMapper<DatabaseDto, Database> {

}
