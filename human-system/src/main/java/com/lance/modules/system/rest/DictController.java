package com.lance.modules.system.rest;

import com.lance.modules.system.service.DictService;
import com.lance.modules.system.service.dto.DictQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "系统：字典管理")
@RequestMapping("/api/dict")
public class DictController {

    private final DictService dictService;

    @ApiOperation("查询字典")
    @GetMapping(value = "/all")
    @PreAuthorize("@el.check('dict:list')")
    public ResponseEntity<Object> queryAll(){
        return new ResponseEntity<>(dictService.queryAll(new DictQueryCriteria()), HttpStatus.OK);
    }
}
