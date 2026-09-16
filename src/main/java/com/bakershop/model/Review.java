package com.bakershop.model;

public class Review {
	private long id;
	private long userId;
	private int cakeId;
	private String reviewerName;
	private double rating;
	private String comment;

	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public long getUserId() { return userId; }
	public void setUserId(long userId) { this.userId = userId; }
	public int getCakeId() { return cakeId; }
	public void setCakeId(int cakeId) { this.cakeId = cakeId; }
	public String getReviewerName() { return reviewerName; }
	public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }
	public double getRating() { return rating; }
	public void setRating(double rating) { this.rating = rating; }
	public String getComment() { return comment; }
	public void setComment(String comment) { this.comment = comment; }
}
