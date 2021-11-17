package com.lance.modules.mnt.rest;

import com.lance.modules.mnt.service.AppService;
import com.lance.modules.mnt.service.dto.AppQueryCriteria;
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

@Api(tags = "运维：应用管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app")
public class AppController {

    private final AppService appService;

    @ApiOperation(value = "查询应用")
    @GetMapping
    @PreAuthorize("@el.check('app:list')")
    public ResponseEntity<Object> query(AppQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(appService.queryAll(criteria,pageable), HttpStatus.OK);
    }
}
