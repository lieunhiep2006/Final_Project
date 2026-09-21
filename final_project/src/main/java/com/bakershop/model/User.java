package com.bakershop.model;

public class User {
    private Long id;
    private String full_name;
    private String phone_number;
    private String password_hash;
    private String address;
    private String role;

    public User() {}
    public User(Long id, String full_name, String phone_number, String password_hash, String address, String role) {
        this.id = id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.password_hash = password_hash;
        this.address = address;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return full_name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setPasswordHash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
