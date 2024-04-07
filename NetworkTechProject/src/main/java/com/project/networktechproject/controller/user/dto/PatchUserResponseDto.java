package com.project.networktechproject.controller.user.dto;

import java.sql.Date;
import java.time.LocalDate;

public class PatchUserResponseDto {
    private long id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;

    public PatchUserResponseDto(long id, String name, String lastName, LocalDate dateOfBirth, String email) {
        this.id = id;
        this.email = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.name = email;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
