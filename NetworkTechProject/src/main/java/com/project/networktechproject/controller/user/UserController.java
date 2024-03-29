package com.project.networktechproject.controller.user;

import com.project.networktechproject.controller.user.dto.GetUserDto;
import com.project.networktechproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getMe(Principal principal) {
        String username = principal.getName();
        GetUserDto userDto = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GetUserDto> getOneById(@PathVariable long id) {
        GetUserDto dto = userService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GetUserDto>> getAllUsers() {
        List<GetUserDto> dto = userService.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
