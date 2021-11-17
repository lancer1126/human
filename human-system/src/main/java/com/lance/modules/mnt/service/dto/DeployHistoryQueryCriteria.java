package com.lance.modules.mnt.service.dto;

import com.lance.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DeployHistoryQueryCriteria{

    /**
     * 精确
     */
    @Query(blurry = "appName,ip,deployUser")
    private String blurry;

    @Query
    private Long deployId;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> deployDate;
}
