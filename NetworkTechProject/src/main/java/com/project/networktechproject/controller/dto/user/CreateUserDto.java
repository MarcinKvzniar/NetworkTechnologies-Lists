package com.project.networktechproject.controller.dto.user;

public class CreateUserDto {
    private String name;
    private String email;

    public CreateUserDto() {
    }

    public CreateUserDto(String name, String email) {
        this.name = email;
        this.email = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

}

