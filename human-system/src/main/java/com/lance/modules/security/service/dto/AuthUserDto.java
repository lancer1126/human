package com.lance.modules.security.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * AuthorUserDto
 * @author lancer1126
 */
@Data
public class AuthUserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";
}
