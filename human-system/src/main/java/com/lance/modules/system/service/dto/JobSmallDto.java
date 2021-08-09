package com.lance.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class JobSmallDto implements Serializable {

    private  long id;

    private String name;
}
