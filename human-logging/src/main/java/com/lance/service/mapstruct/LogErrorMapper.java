package com.lance.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.domain.Log;
import com.lance.service.dto.LogErrorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogErrorMapper extends BaseMapper<LogErrorDTO, Log> {
}
