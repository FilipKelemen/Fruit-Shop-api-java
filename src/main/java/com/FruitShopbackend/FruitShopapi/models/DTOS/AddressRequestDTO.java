package com.FruitShopbackend.FruitShopapi.models.DTOS;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressRequestDTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotEmpty(message = "Email should not be empty or null")
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String completeStreet;
    @NotNull
    private String country;
    @NotNull
    private String state;
    @NotNull
    private String city;
    @NotNull
    private String postalCode;
    private String company;

    public AddressRequestDTO() {
    }

    public AddressRequestDTO(String firstName, String lastName, String email, String phoneNumber,
                             String completeStreet, String country, String state,
                             String city, String postalCode, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.completeStreet = completeStreet;
        this.country = country;
        this.state = state;
        this.city = city;
        this.postalCode = postalCode;
        this.company = company;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompleteStreet() {
        return completeStreet;
    }

    public void setCompleteStreet(String completeStreet) {
        this.completeStreet = completeStreet;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "AddressRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", completeStreet='" + completeStreet + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
