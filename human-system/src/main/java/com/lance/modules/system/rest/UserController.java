package com.lance.modules.system.rest;

import com.lance.modules.system.domain.Dept;
import com.lance.modules.system.service.DataService;
import com.lance.modules.system.service.DeptService;
import com.lance.modules.system.service.UserService;
import com.lance.modules.system.service.dto.UserQueryCriteria;
import com.lance.utils.PageUtil;
import com.lance.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DeptService deptService;
    private final DataService dataService;

    @ApiOperation("查询用户")
    @GetMapping
    @PreAuthorize("@el.check('user:list')")
    public ResponseEntity<Object> query(UserQueryCriteria criteria, Pageable pageable) {
        if (!ObjectUtils.isEmpty(criteria.getDeptId())) {
            criteria.getDeptIds().add(criteria.getDeptId());
            List<Dept> data = deptService.findByPid(criteria.getDeptId());
            // 将子节点的Id都加入到集合中
            criteria.getDeptIds().addAll(deptService.getDeptChildren(data));
        }

        // 数据权限
        List<Long> dataScopes = dataService.getDeptIds(userService.findByName(SecurityUtils.getCurrentUsername()));
        if (!CollectionUtils.isEmpty(criteria.getDeptIds()) && !CollectionUtils.isEmpty(dataScopes)) {
            //取交集
            criteria.getDeptIds().retainAll(dataScopes);
            if (!CollectionUtils.isEmpty(criteria.getDeptIds())) {
                return new ResponseEntity<>(userService.queryAll(criteria, pageable), HttpStatus.OK);
            }
        } else {
            // 取并集
            criteria.getDeptIds().addAll(dataScopes);
            return new ResponseEntity<>(userService.queryAll(criteria,pageable),HttpStatus.OK);
        }
        return new ResponseEntity<>(PageUtil.toPage(null, 0), HttpStatus.OK);
    }
}
