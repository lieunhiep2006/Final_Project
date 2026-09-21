package com.bakershop.model;
import java.util.List;
import java.util.ArrayList;
import java.sql.Time;

public class Order {
	private Long id;
	private Long userId;
    private Long voucherId;
	private String deliveryAddress;
	private String deliveryPhone;
	private Time deliveryTime;
	private double subtotal;
	private double discountAmount;
	private double shippingFee;
	private double totalAmount;
	private String status;
	private String paymentStatus;
	
    public Order() {}
    public Order(Long id, Long userId, Long voucherId, Long storeId, String deliveryAddress, String deliveryPhone,Time deliveryTime, double subtotal, double discountAmount, double shippingFee, double totalAmount, String status, String paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.voucherId = voucherId;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhone = deliveryPhone;
        this.deliveryTime = deliveryTime;
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }

	private List<OrderItem> items = new ArrayList<>();

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }

    public Long getVoucherId() { return voucherId; }
	public void setVoucherId(Long voucherId) { this.voucherId = voucherId; }

	public String getDeliveryAddress() { return deliveryAddress; }
	public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

	public String getDeliveryPhone() { return deliveryPhone; }
	public void setDeliveryPhone(String deliveryPhone) { this.deliveryPhone = deliveryPhone; }
    
	public Time getDeliveryTime() { return deliveryTime; }
	public void setDeliveryTime(Time deliveryTime) { this.deliveryTime = deliveryTime; }

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
	
	public List<OrderItem> getItems() { return items; }
	public void setItems(List<OrderItem> items) { this.items = items; }
}