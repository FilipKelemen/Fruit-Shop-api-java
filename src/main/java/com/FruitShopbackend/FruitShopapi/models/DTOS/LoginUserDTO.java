package com.FruitShopbackend.FruitShopapi.models.DTOS;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginUserDTO {

    @NotEmpty( message = "Email should not be empty or null")
    private String email;

    @NotNull
    private String password;

    public LoginUserDTO(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
