package com.example.coinzipp.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isDealer;
    private String companyName;
    private String companyaddress;
    private String companyCity;
    private String companyState;
    private String companyZip;
    private String companyPhone;

    public User(String email, String password, String firstName, String lastName, String phoneNumber, boolean isDealer, String companyName, String companyaddress, String companyCity, String companyState, String companyZip, String companyPhone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isDealer = isDealer;
        this.companyName = companyName;
        this.companyaddress = companyaddress;
        this.companyCity = companyCity;
        this.companyState = companyState;
        this.companyZip = companyZip;
        this.companyPhone = companyPhone;
    }
    public User(String email, String password, String firstName, String lastName, String phoneNumber, boolean isDealer) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isDealer = isDealer;
    }
}
