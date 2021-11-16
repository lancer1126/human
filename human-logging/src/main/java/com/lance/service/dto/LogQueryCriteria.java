package com.lance.service.dto;

import com.lance.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class LogQueryCriteria {

    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    private String logType;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}