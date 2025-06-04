package com.tut.common.dto;

import com.tut.common.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private Role role;
}
