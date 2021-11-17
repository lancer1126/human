package com.lance.modules.mnt.service.dto;

import com.lance.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AppDto extends BaseDTO implements Serializable {

    /**
     * 应用编号
     */
    private Long id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 上传目录
     */
    private String uploadPath;

    /**
     * 部署目录
     */
    private String deployPath;

    /**
     * 备份目录
     */
    private String backupPath;

    /**
     * 启动脚本
     */
    private String startScript;

    /**
     * 部署脚本
     */
    private String deployScript;

}
