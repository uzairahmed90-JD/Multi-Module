package com.tut.service.impl;

import com.tut.common.dto.UserDto;
import com.tut.common.entity.User;
import com.tut.common.exception.ResourceNotFoundException;
import com.tut.common.mapper.UserMapper;
import com.tut.repository.UserRepository;
import com.tut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Override
    public UserDto craeteUser(UserDto dto) {
        User us =UserMapper.toEntity(dto,passwordEncoder);
        return UserMapper.toDTO(userRepository.save(us));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return  userRepository.findAll().stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User us=userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found "));
        return UserMapper.toDTO(us);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        UserMapper.updateUserFromDto(userDto, user, passwordEncoder);
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
