package com.lance.modules.mnt.rest;

import com.lance.modules.mnt.service.DatabaseService;
import com.lance.modules.mnt.service.dto.DatabaseQueryCriteria;
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

@Api(tags = "运维：数据库管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    @ApiOperation(value = "查询数据库")
    @GetMapping
    @PreAuthorize("@el.check('database:list')")
    public ResponseEntity<Object> query(DatabaseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(databaseService.queryAll(criteria,pageable), HttpStatus.OK);
    }
}
