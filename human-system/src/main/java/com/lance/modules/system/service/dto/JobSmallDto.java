package com.lance.modules.system.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JobSmallDto implements Serializable {

    private  long id;

    private String name;
}
