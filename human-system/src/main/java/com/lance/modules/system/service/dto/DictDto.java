package com.lance.modules.system.service.dto;

import com.lance.base.BaseDTO;
import com.lance.modules.system.service.dto.DictDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DictDto extends BaseDTO implements Serializable {

    private Long id;

    private List<DictDetailDto> dictDetails;

    private String name;

    private String description;
}
