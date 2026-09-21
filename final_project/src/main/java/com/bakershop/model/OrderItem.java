package com.bakershop.model;

public class OrderItem {
	private Long id;
    private Long orderId;
	private Long cakeId;
	private String size;
	private int quantity;
	private Double price;
	private String note;

    public OrderItem() {}
    public OrderItem(Long id, Long orderId, Long cakeId, String size, int quantity, Double price, String note) {
        this.id = id;
        this.orderId = orderId;
        this.cakeId = cakeId;
        this.size = size;
        this.quantity = quantity;
        this.price =price;
        this.note = note;
    }

	public long getId() { return id; }
	public void setId(long id) { this.id = id; }

    public long getOrderId() { return orderId; }
	public void setOrderId(long orderId) { this.orderId = orderId; }

	public Long getCakeId() { return cakeId; }
	public void setCakeId(Long cakeId) { this.cakeId = cakeId; }

	public String getSize() { return size; }
	public void setSize(String size) { this.size = size; }

	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }

	public Double getPrice() { return price; }
	public void setPrice(Double price) { this.price = price; }

	public String getNote() { return note; }
	public void setNote(String note) { this.note = note; }

}