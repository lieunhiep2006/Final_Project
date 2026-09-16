package com.bakershop.model;

public class Voucher {
	private long id;
	private String code;
	private String discountType;
	private double discountValue;
	private double minOrderAmount;
	private Double maxDiscountAmount;
	private Integer usageLimit;
	private java.sql.Date startDate;
	private java.sql.Date endDate;

	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	public String getDiscountType() { return discountType; }
	public void setDiscountType(String discountType) { this.discountType = discountType; }
	public double getDiscountValue() { return discountValue; }
	public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }
	public double getMinOrderAmount() { return minOrderAmount; }
	public void setMinOrderAmount(double minOrderAmount) { this.minOrderAmount = minOrderAmount; }
	public Double getMaxDiscountAmount() { return maxDiscountAmount; }
	public void setMaxDiscountAmount(Double maxDiscountAmount) { this.maxDiscountAmount = maxDiscountAmount; }
	public Integer getUsageLimit() { return usageLimit; }
	public void setUsageLimit(Integer usageLimit) { this.usageLimit = usageLimit; }
	public java.sql.Date getStartDate() { return startDate; }
	public void setStartDate(java.sql.Date startDate) { this.startDate = startDate; }
	public java.sql.Date getEndDate() { return endDate; }
	public void setEndDate(java.sql.Date endDate) { this.endDate = endDate; }
}
