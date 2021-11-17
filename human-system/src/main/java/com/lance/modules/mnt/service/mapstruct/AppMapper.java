package com.lance.modules.mnt.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.mnt.domain.App;
import com.lance.modules.mnt.service.dto.AppDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppMapper extends BaseMapper<AppDto, App> {

}

