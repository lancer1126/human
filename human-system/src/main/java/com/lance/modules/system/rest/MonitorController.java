package com.lance.modules.system.rest;

import com.lance.modules.system.service.MonitorService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统-服务监控管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monitor")
public class MonitorController {

    private final MonitorService monitorService;

    @GetMapping
    @PreAuthorize("@el.check('monitor:list')")
    public ResponseEntity<Object> query() {
        return new ResponseEntity<>(monitorService.getServers(), HttpStatus.OK);
    }
}
