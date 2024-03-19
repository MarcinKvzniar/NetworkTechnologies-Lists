package com.project.networktechproject.service;

import com.project.networktechproject.commonTypes.UserRole;
import com.project.networktechproject.controller.dto.login.LoginDto;
import com.project.networktechproject.controller.dto.login.LoginResponseDto;
import com.project.networktechproject.controller.dto.register.RegisterDto;
import com.project.networktechproject.controller.dto.register.RegisterResponseDto;
import com.project.networktechproject.infrastructure.entity.AuthEntity;
import com.project.networktechproject.infrastructure.entity.UserEntity;
import com.project.networktechproject.infrastructure.repository.AuthRepository;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public RegisterResponseDto register(RegisterDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        UserEntity createdUser = userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setUsername(dto.getUsername());
        authEntity.setPassword(dto.getPassword());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(createdUser);

        AuthEntity createdAuth = authRepository.save(authEntity);

        return new RegisterResponseDto(createdAuth.getUsername(), createdAuth.getRole());
    }

    public LoginResponseDto login(LoginDto dto) {
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername())
                .orElseThrow(RuntimeException::new);

        if (!authEntity.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException();
        }

        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDto(token);
    }

}
