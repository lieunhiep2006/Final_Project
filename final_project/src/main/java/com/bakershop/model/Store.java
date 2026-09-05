package com.bakershop.model;

public class Store {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String openingHours;
    public Store(){}
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public String getOpeningHours(){
        return openingHours;
    }
    public void setOpeningHours(String openingHours){
        this.openingHours=openingHours;
    }
}
