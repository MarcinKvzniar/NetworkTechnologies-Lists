package com.project.networktechproject.service;

import com.project.networktechproject.infrastructure.entity.UserEntity;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() { return userRepository.findAll(); }

}