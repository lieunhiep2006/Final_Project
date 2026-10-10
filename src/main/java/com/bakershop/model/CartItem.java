package com.bakershop.model;

public class CartItem {
    private Long id;
    private Long cartId;
    private Long cakeId;
    private String size;
    private int quantity;
    private String note;
    private Cake cake;
    
    public CartItem() {}
    public CartItem(Long id, Long cartId, Long cakeId, String size, int quantity, String note) {
        this.id = id;
        this.cartId = cartId;
        this.cakeId = cakeId;
        this.size = size;
        this.quantity = quantity;
        this.note = note;
    }

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }

    public Long getCartId() { return this.cartId; }
    public void setCartId(Long cartId) { this.cartId = cartId; }

    public Long getCakeId() { return this.cakeId; }
    public void setCakeId(Long cakeId) { this.cakeId = cakeId; }

    public String getSize() { return this.size; }
    public void setSize(String size) { this.size = size; }

    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getNote() { return this.note; }
    public void setNote(String note) { this.note = note; }

    public Cake getCake() { return cake; }
    public void setCake(Cake cake) { this.cake = cake; }

    public double getLineTotal() {
        return cake != null ? cake.getPrice() * quantity : 0;
    }

    
}
