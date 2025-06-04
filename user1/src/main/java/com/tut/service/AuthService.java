package com.tut.service;

import com.tut.common.dto.UserDto;
import com.tut.common.dto.AuthRequest;
import com.tut.common.dto.AuthResponse;
import com.tut.common.entity.User;
import com.tut.common.exception.ResourceNotFoundException;
import com.tut.common.util.JwtUtil;
import com.tut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    public void register(UserDto userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceNotFoundException("Email already exists");
        }

        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(userDTO.getRole())
                .build();

        userRepository.save(user);
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }
}
