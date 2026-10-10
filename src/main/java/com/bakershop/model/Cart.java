package com.bakershop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long id;
    private Long userId;
    private List<CartItem> items = new ArrayList<>();
    public Cart() {}
    public Cart(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() { return this.id; }
    public Long getUserId() { return this.userId; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }


}
