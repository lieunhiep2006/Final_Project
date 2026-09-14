package com.bakershop.model;

public class Cake {
    private int id;
    private String name;
    private String description;
    private Double price;
    private int stockQuantity;
    private int categoryId;

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
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price=price;
    }
    public int getStockQuantity(){
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity){
        this.stockQuantity=stockQuantity;
    }
    public int getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(int categoryId){
        this.categoryId=categoryId;
    }
    public String getLocalImageFile(){
        String[] images = {
            "strawberrymousse.jpg", "brownies.jpg", "mangomousse.jpg", "cupcake.jpg",
            "bluefudge.jpg", "creampuffs.jpg", "matcha.jpg", "corncake.jpg",
            "lemoncheesecake.jpg", "mangomousse.jpg", "oreocheesecake.jpg",
            "basquecheesecake.jpg", "lemoncheesecake.jpg", "oreocheesecake.jpg",
            "cupcake.jpg"
        };
        return id > 0 && id <= images.length ? images[id - 1] : "bakery1.jpg";
    }
}