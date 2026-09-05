package com.bakershop.model;

public class Cake {
    private int id;
    private String name;
    private Double price;
    private String imageUrl;

    public Cake(){

    }
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
    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price=price;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl=imageUrl;
    }
}