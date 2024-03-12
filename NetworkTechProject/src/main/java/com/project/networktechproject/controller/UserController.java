package com.project.networktechproject.controller;

import com.project.networktechproject.infrastructure.dto.UserDTO;
import com.project.networktechproject.infrastructure.entity.UserEntity;
import com.project.networktechproject.infrastructure.repository.UserRepository;
import com.project.networktechproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "Saved";
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

}
