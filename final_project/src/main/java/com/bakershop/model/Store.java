package com.bakershop.model;

public class Store {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String openingHours;
    private String imageUrl;

    public Store() {}

    public Store(Long id, String name, String address, String phoneNumber, String openingHours, String imageUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }

    public String getName() {return this.name; }
    public void setName(String name) {this.name = name; }

    public String getAddress() {return this.address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() {return this.phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }

    public String getOpeningHours() {return this.openingHours; }
    public void setOpeningHours(String openingHours) {this.openingHours = openingHours; }

    public String getImageUrl() {return this.imageUrl; }
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl; }
}
