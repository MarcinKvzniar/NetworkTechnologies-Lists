package com.project.networktechproject.controller.user.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import java.sql.Date;

public class PatchUserDto {
    private JsonNullable<String> name;
    private JsonNullable<String> lastName;
    private JsonNullable<Date> dateOfBirth;
    private JsonNullable<String> email;

    public PatchUserDto(JsonNullable<String> name, JsonNullable<String> lastName, JsonNullable<Date> dateOfBirth, JsonNullable<String> email) {
        this.email = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.name = email;
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

    public JsonNullable<Date> getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(JsonNullable<Date> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public JsonNullable<String> getEmail() {
        return email;
    }

    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }
}
