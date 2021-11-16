package com.lance.modules.system.rest;

import com.lance.modules.system.service.MonitorService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统-服务监控管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monitor")
public class MonitorController {

    private final MonitorService monitorService;
}
