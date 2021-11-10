package com.lance.modules.system.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.system.domain.Job;
import com.lance.modules.system.service.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends BaseMapper<JobDto, Job> {
}
