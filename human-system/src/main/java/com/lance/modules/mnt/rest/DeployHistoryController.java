package com.lance.modules.mnt.rest;

import com.lance.modules.mnt.service.DeployHistoryService;
import com.lance.modules.mnt.service.dto.DeployHistoryQueryCriteria;
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

@Api(tags = "运维：部署历史管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deployHistory")
public class DeployHistoryController {

    private final DeployHistoryService deployhistoryService;

    @ApiOperation(value = "查询部署历史")
    @GetMapping
    @PreAuthorize("@el.check('deployHistory:list')")
    public ResponseEntity<Object> query(DeployHistoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(deployhistoryService.queryAll(criteria,pageable), HttpStatus.OK);
    }
}
