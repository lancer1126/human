package com.lance.modules.mnt.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.mnt.domain.ServerDeploy;
import com.lance.modules.mnt.service.dto.ServerDeployDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServerDeployMapper extends BaseMapper<ServerDeployDto, ServerDeploy> {
}
