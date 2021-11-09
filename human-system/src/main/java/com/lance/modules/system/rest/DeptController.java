package com.lance.modules.system.rest;

import com.lance.modules.system.service.DeptService;
import com.lance.modules.system.service.dto.DeptDto;
import com.lance.modules.system.service.dto.DeptQueryCriteria;
import com.lance.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "系统：部门管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dept")
public class DeptController {

    private final DeptService deptService;

    @ApiOperation("查询部门")
    @GetMapping
    @PreAuthorize("@el.check('user:list','dept:list')")
    public ResponseEntity<Object> query(DeptQueryCriteria criteria) throws Exception {
        List<DeptDto> deptDtos = deptService.queryAll(criteria, true);
        return new ResponseEntity<>(PageUtil.toPage(deptDtos, deptDtos.size()), HttpStatus.OK);
    }
}
