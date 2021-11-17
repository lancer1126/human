package com.lance.modules.mnt.service.dto;

import com.lance.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DatabaseQueryCriteria{

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /**
     * 精确
     */
    @Query
    private String jdbcUrl;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
