package com.tut.common.mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.tut.common.dto.UserDto;
import com.tut.common.entity.User;

public class UserMapper {

        public static UserDto toDTO(User user){
            if (user == null) return null;
            UserDto dto =new UserDto();
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            return dto;
        }

        public static User toEntity(UserDto dto, PasswordEncoder pe){
            if(dto ==null) return null;
            return User.builder()
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .password(pe.encode(dto.getPassword()))
                    .role(dto.getRole())
                    .build();
        }

        public static void updateUserFromDto(UserDto dto, User user, PasswordEncoder passwordEncoder) {
            if (dto.getName() != null) user.setName(dto.getName());
            if (dto.getEmail() != null) user.setEmail(dto.getEmail());
            if (dto.getRole() != null) user.setRole(dto.getRole());
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
        }

    }

