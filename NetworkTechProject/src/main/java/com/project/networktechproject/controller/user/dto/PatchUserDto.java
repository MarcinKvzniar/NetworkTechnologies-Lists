package com.project.networktechproject.controller.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.openapitools.jackson.nullable.JsonNullable;

import java.sql.Date;
import java.time.LocalDate;

public class PatchUserDto {
    @Schema(name = "name", example = "John")
    private JsonNullable<String> name;

    @Schema(name = "lastName", example = "Doe")
    private JsonNullable<String> lastName;

    @Schema(name = "dateOfBirth", example = "1990-12-31")
    private JsonNullable<LocalDate> dateOfBirth;

    @Schema(name = "email", example = "email@email.com")
    private JsonNullable<String> email;

    public PatchUserDto(JsonNullable<String> name, JsonNullable<String> lastName, JsonNullable<LocalDate> dateOfBirth, JsonNullable<String> email) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public PatchUserDto() {
    }

    public JsonNullable<String> getName() {
        return name;
    }

    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getLastName() {
        return lastName;
    }

    public void setLastName(JsonNullable<String> lastName) {
        this.lastName = lastName;
    }

    public JsonNullable<LocalDate> getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(JsonNullable<LocalDate> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public JsonNullable<String> getEmail() {
        return email;
    }

    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }
}
