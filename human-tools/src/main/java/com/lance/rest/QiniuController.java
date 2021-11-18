package com.lance.rest;

import com.lance.service.QiNiuService;
import com.lance.service.dto.QiniuQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "工具：七牛云存储管理")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qiNiuContent")
public class QiniuController {

    private final QiNiuService qiNiuService;

    @ApiOperation("查询文件")
    @GetMapping
    public ResponseEntity<Object> query(QiniuQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(qiNiuService.queryAll(criteria,pageable), HttpStatus.OK);
    }
}
