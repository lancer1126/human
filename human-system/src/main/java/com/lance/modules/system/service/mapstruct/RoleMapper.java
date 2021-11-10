package com.lance.modules.system.service.mapstruct;

import com.lance.base.BaseMapper;
import com.lance.modules.system.domain.Role;
import com.lance.modules.system.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {
}
