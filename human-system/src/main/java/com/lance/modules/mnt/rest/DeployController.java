package com.lance.modules.mnt.rest;

import com.lance.modules.mnt.service.DeployService;
import com.lance.modules.mnt.service.dto.DeployQueryCriteria;
import com.lance.utils.FileUtil;
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

@Api(tags = "运维：部署管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deploy")
public class DeployController {

    private final String fileSavePath = FileUtil.getTmpDirPath()+"/";
    private final DeployService deployService;

    @ApiOperation(value = "查询部署")
    @GetMapping
    @PreAuthorize("@el.check('deploy:list')")
    public ResponseEntity<Object> query(DeployQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(deployService.queryAll(criteria,pageable), HttpStatus.OK);
    }

}
