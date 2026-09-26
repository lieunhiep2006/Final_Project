package com.bakershop.model;
import java.sql.Date;
public class Voucher {
	private Long id;
	private String code;
	private String discountType;
	private Double discountValue;
	private Double minOrderAmount;
	private Double maxDiscountAmount;
	private int usageLimit;
	private Date startDate;
	private Date endDate;

    public Voucher() {}
    public Voucher(Long id, String code, String discountType, Double discountValue, Double minOrderAmout, Double maxDiscountAmout, int usageLimit, Date startDate, Date endDate) {
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minOrderAmount = minOrderAmout;
        this.maxDiscountAmount = maxDiscountAmout;
        this.usageLimit = usageLimit;
        this.startDate = startDate;
        this.endDate = endDate;
    }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }

	public String getDiscountType() { return discountType; }
	public void setDiscountType(String discountType) { this.discountType = discountType; }

	public Double getDiscountValue() { return discountValue; }
	public void setDiscountValue(Double discountValue) { this.discountValue = discountValue; }

	public Double getMinOrderAmount() { return minOrderAmount; }
	public void setMinOrderAmount(Double minOrderAmount) { this.minOrderAmount = minOrderAmount; }

	public Double getMaxDiscountAmount() { return maxDiscountAmount; }
	public void setMaxDiscountAmount(Double maxDiscountAmount) { this.maxDiscountAmount = maxDiscountAmount; }

	public int getUsageLimit() { return usageLimit; }
	public void setUsageLimit(int usageLimit) { this.usageLimit = usageLimit; }

	public Date getStartDate() { return startDate; }
	public void setStartDate(Date startDate) { this.startDate = startDate; }

	public Date getEndDate() { return endDate; }
	public void setEndDate(Date endDate) { this.endDate = endDate; }
}