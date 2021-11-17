package com.lance.modules.mnt.rest;

import com.lance.modules.mnt.service.ServerDeployService;
import com.lance.modules.mnt.service.dto.ServerDeployQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "运维：服务器管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/serverDeploy")
public class ServerDeployController {

    private final ServerDeployService serverDeployService;

    @ApiOperation(value = "查询服务器")
    @GetMapping
    @PreAuthorize("@el.check('serverDeploy:list')")
    public ResponseEntity<Object> query(ServerDeployQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(serverDeployService.queryAll(criteria,pageable), HttpStatus.OK);
    }
}
