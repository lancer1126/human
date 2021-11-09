package com.lance.modules.system.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.system.domain.Dict;
import com.lance.modules.system.service.dto.DictSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictSmallMapper extends BaseMapper<DictSmallDto, Dict> {

}
