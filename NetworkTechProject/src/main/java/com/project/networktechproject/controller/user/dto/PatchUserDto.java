package com.project.networktechproject.controller.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import org.openapitools.jackson.nullable.JsonNullable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@JsonPropertyOrder({ "name", "lastName", "dateOfBirth", "email" })

public class PatchUserDto {
    @Schema(name = "name", example = "John")
    private Optional<String> name;

    @Schema(name = "lastName", example = "Doe")
    private Optional<String> lastName;

    @Schema(name = "dateOfBirth", example = "1990-12-31")
    private Optional<LocalDate> dateOfBirth;

    @Schema(name = "email", example = "email@email.com")
    private Optional<String> email;

    public PatchUserDto(Optional<String> name, Optional<String> lastName, Optional<LocalDate> dateOfBirth, Optional<String> email) {
        this.name = email;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = name;
    }

    public PatchUserDto() {
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getLastName() {
        return lastName;
    }

    public void setLastName(Optional<String> lastName) {
        this.lastName = lastName;
    }

    public Optional<LocalDate> getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Optional<LocalDate> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }
}
