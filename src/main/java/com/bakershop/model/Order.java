package com.bakershop.model;

public class Order {
	private long id;
	private long userId;
	private String storeName;
	private String deliveryAddress;
	private String deliveryPhone;
	private java.sql.Timestamp deliveryTime;
	private double subtotal;
	private double discountAmount;
	private double shippingFee;
	private double totalAmount;
	private String status;
	private String paymentStatus;
	private String voucherCode;
	private java.util.List<OrderItem> items = new java.util.ArrayList<>();
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public long getUserId() { return userId; }
	public void setUserId(long userId) { this.userId = userId; }
	public String getStoreName() { return storeName; }
	public void setStoreName(String storeName) { this.storeName = storeName; }
	public String getDeliveryAddress() { return deliveryAddress; }
	public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
	public String getDeliveryPhone() { return deliveryPhone; }
	public void setDeliveryPhone(String deliveryPhone) { this.deliveryPhone = deliveryPhone; }
	public java.sql.Timestamp getDeliveryTime() { return deliveryTime; }
	public void setDeliveryTime(java.sql.Timestamp deliveryTime) { this.deliveryTime = deliveryTime; }
	public double getSubtotal() { return subtotal; }
	public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
	public double getDiscountAmount() { return discountAmount; }
	public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }
	public double getShippingFee() { return shippingFee; }
	public void setShippingFee(double shippingFee) { this.shippingFee = shippingFee; }
	public double getTotalAmount() { return totalAmount; }
	public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public String getPaymentStatus() { return paymentStatus; }
	public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
	public String getVoucherCode() { return voucherCode; }
	public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
	public java.util.List<OrderItem> getItems() { return items; }
	public void setItems(java.util.List<OrderItem> items) { this.items = items; }
}
