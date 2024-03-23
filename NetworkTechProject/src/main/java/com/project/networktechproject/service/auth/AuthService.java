package com.project.networktechproject.service.auth;

import com.project.networktechproject.controller.auth.dto.LoginDto;
import com.project.networktechproject.controller.auth.dto.LoginResponseDto;
import com.project.networktechproject.controller.auth.dto.RegisterDto;
import com.project.networktechproject.controller.auth.dto.RegisterResponseDto;
import com.project.networktechproject.infrastructure.entity.AuthEntity;
import com.project.networktechproject.infrastructure.entity.UserEntity;
import com.project.networktechproject.infrastructure.repository.AuthRepository;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import com.project.networktechproject.service.auth.error.PasswordNotMatching;
import com.project.networktechproject.service.auth.error.UserAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponseDto register(RegisterDto dto) {
        Optional<AuthEntity> existingAuth = authRepository.findByUsername(dto.getUsername());

        if (existingAuth.isPresent()) {
            throw UserAlreadyExists.create(dto.getUsername());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        authEntity.setPassword(hashPassword);
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);
        authRepository.save(authEntity);

        return new RegisterResponseDto(userEntity.getId(), authEntity.getUsername(), authEntity.getRole());
    }

    public LoginResponseDto login(LoginDto dto) {
        AuthEntity authEntity = authRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(RuntimeException::new);

        if (!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())) {
            throw PasswordNotMatching.create();
        }

        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDto(token);
    }

}
