package com.project.networktechproject.service;

import com.project.networktechproject.infrastructure.dto.BookDTO;
import com.project.networktechproject.infrastructure.dto.UserDTO;
import com.project.networktechproject.infrastructure.entity.BookEntity;
import com.project.networktechproject.infrastructure.entity.UserEntity;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() { return userRepository.findAll(); }

    public void saveUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRole(userDTO.getRole());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setName(userDTO.getName());
        userRepository.save(userEntity);
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userDTO.getPassword());
        userDTO.setRole(userDTO.getRole());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setName(userDTO.getName());
        return userDTO;
    }

}