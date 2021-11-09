package com.lance.modules.system.service.dto;

import com.lance.annotation.Query;
import lombok.Data;

@Data
public class DictQueryCriteria {

    @Query(blurry = "name,description")
    private String blurry;
}