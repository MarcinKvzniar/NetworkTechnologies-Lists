//package com.project.networktechproject.service;
//
//import com.project.networktechproject.controller.dto.user.CreateUserDto;
//import com.project.networktechproject.controller.dto.user.CreateUserResponseDto;
//import com.project.networktechproject.controller.dto.user.GetUserDto;
//import com.project.networktechproject.infrastructure.entity.UserEntity;
//import com.project.networktechproject.infrastructure.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
////    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
////        this.authenticationManager = authenticationManager;
//    }
//
//    public List<GetUserDto> getAll() {
//        var users = userRepository.findAll();
//
//        return users.stream().map((user) -> new GetUserDto(
//                user.getId(),
//                user.getUsername(),
//                user.getPassword(),
//                user.getRole(),
//                user.getEmail(),
//                user.getName()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    public GetUserDto getOne(String username) {
//        var user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return new GetUserDto(
//                user.getId(),
//                user.getUsername(),
//                user.getPassword(),
//                user.getRole(),
//                user.getEmail(),
//                user.getName()
//        );
//    }
//
//    public CreateUserResponseDto create(CreateUserDto user) {
//        var userEntity = new UserEntity();
//        userEntity.setUsername(user.getUsername());
//        userEntity.setPassword(user.getPassword());
//        userEntity.setRole(user.getRole());
//        userEntity.setEmail(user.getEmail());
//        userEntity.setName(user.getName());
//
//        var newUser = userRepository.save(userEntity);
//
//        return new CreateUserResponseDto(
//                newUser.getId(),
//                newUser.getUsername(),
//                newUser.getPassword(),
//                newUser.getRole(),
//                newUser.getEmail(),
//                newUser.getName()
//        );
//    }
//
//    public void delete(long id) {
//        if (!userRepository.existsById(id)) {
//            throw new RuntimeException();
//        }
//        userRepository.deleteById(id);
//    }
//
//}