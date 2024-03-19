package com.project.networktechproject.controller.dto.register;

import com.project.networktechproject.commonTypes.UserRole;

public class RegisterResponseDto {
    private String username;
    private UserRole role;

    public RegisterResponseDto(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
