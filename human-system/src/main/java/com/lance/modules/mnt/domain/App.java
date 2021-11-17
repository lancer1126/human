package com.lance.modules.mnt.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.lance.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="mnt_app")
public class App extends BaseEntity implements Serializable {

    @Id
    @Column(name = "app_id")
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "端口")
    private int port;

    @ApiModelProperty(value = "上传路径")
    private String uploadPath;

    @ApiModelProperty(value = "部署路径")
    private String deployPath;

    @ApiModelProperty(value = "备份路径")
    private String backupPath;

    @ApiModelProperty(value = "启动脚本")
    private String startScript;

    @ApiModelProperty(value = "部署脚本")
    private String deployScript;

    public void copy(App source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}