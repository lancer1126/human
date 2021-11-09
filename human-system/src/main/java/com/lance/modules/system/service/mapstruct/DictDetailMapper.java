package com.lance.modules.system.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.system.domain.DictDetail;
import com.lance.modules.system.service.dto.DictDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {DictSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDto, DictDetail> {

}
