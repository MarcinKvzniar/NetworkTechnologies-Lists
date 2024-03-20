package com.project.networktechproject.service;

import com.project.networktechproject.controller.dto.user.GetUserDto;
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

    public List<GetUserDto> getAll() {
        var users = userRepository.findAll();

        return users
                .stream()
                .map(user -> new GetUserDto(
                        user.getId(),
                        user.getEmail(),
                        user.getName()
                ))
                .collect(Collectors.toList());
    }

    public GetUserDto getOne(long id) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new GetUserDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }

    public void delete(long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException();
        }
        userRepository.deleteById(id);
    }

}