package com.lance.modules.system.rest;

import com.lance.modules.system.service.DictDetailService;
import com.lance.modules.system.service.dto.DictDetailQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统：字典详情管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dictDetail")
public class DictDetailController {

    private final DictDetailService dictDetailService;

    @ApiOperation("查询字典详情")
    @GetMapping
    public ResponseEntity<Object> query(DictDetailQueryCriteria criteria,
                                        @PageableDefault(sort = {"dictSort"}, direction = Sort.Direction.ASC) Pageable pageable){
        return new ResponseEntity<>(dictDetailService.queryAll(criteria,pageable), HttpStatus.OK);
    }
}
