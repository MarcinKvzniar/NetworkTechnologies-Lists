package com.project.networktechproject.controller.user.dto;

public class GetUserDto {
    private long id;
    private String name;
    private String email;

    public GetUserDto() {
    }

    public GetUserDto(long id, String name, String email) {
        this.id = id;
        this.name = email;
        this.email = name;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
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
