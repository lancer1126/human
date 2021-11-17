package com.lance.modules.mnt.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.mnt.domain.DeployHistory;
import com.lance.modules.mnt.service.dto.DeployHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeployHistoryMapper extends BaseMapper<DeployHistoryDto, DeployHistory> {
}
