package com.project.networktechproject.service.user;

import com.project.networktechproject.controller.user.dto.GetUserDto;
import com.project.networktechproject.infrastructure.entity.UserEntity;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import com.project.networktechproject.service.user.error.UserNotFound;
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

    public GetUserDto getOne(long id) {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> UserNotFound.create(id));

        return mapUser(user);
    }

    public List<GetUserDto> getAll() {
        List<UserEntity> users = userRepository.findAll();

        return users
                .stream()
                .map(this::mapUser)
                .collect(Collectors.toList());
    }

    public void delete(long id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFound.create(id);
        }
        userRepository.deleteById(id);
    }

    private GetUserDto mapUser(UserEntity user) {
        return new GetUserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail()
        );
    }

}