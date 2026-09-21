package com.bakershop.model;

public class Review {
	private Long id;
	private Long userId;
	private Long cakeId;
	private Double rating;
	private String comment;

    public Review() {}
    public Review(Long id, Long userId, Long cakeId, Double rating, String comment) {
        this.id = id;
        this.userId = userId;
        this.cakeId = cakeId;
        this.rating = rating;
        this.comment = comment;
    }


	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }

	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }

	public Long getCakeId() { return cakeId; }
	public void setCakeId(Long cakeId) { this.cakeId = cakeId; }
	
	public Double getRating() { return rating; }
	public void setRating(Double rating) { this.rating = rating; }

	public String getComment() { return comment; }
	public void setComment(String comment) { this.comment = comment; }
}