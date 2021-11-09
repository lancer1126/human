package com.lance.modules.system.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.system.domain.Dept;
import com.lance.modules.system.service.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {
}
