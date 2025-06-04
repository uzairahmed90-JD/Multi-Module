package com.tut.service;

import com.tut.common.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto craeteUser(UserDto dto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUserById(Long id);
}
