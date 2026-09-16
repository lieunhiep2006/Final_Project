package com.bakershop.model;

public class OrderItem {
	private long id;
	private int cakeId;
	private String cakeName;
	private String size;
	private int quantity;
	private double price;
	private String note;
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public int getCakeId() { return cakeId; }
	public void setCakeId(int cakeId) { this.cakeId = cakeId; }
	public String getCakeName() { return cakeName; }
	public void setCakeName(String cakeName) { this.cakeName = cakeName; }
	public String getSize() { return size; }
	public void setSize(String size) { this.size = size; }
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	public String getNote() { return note; }
	public void setNote(String note) { this.note = note; }
	public double getLineTotal() { return price * quantity; }
}
