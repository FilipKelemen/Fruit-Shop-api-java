package com.FruitShopbackend.FruitShopapi.models.DTOS;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterUserDTO {

    @NotEmpty( message = "Email should not be empty or null")
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String confirmedPassword;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public RegisterUserDTO() {
    }

    public RegisterUserDTO(String email, String password, String confirmedPassword, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "RegisterUserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmedPassword='" + confirmedPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
