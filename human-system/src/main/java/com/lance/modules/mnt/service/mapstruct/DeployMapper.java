package com.lance.modules.mnt.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.mnt.domain.Deploy;
import com.lance.modules.mnt.service.dto.DeployDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {AppMapper.class, ServerDeployMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeployMapper extends BaseMapper<DeployDto, Deploy> {
}
