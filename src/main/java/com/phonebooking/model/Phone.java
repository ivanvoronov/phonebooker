package com.phonebooking.model;

import java.time.LocalDateTime;

public class Phone {

	private String model;
	private boolean isAvailable;
	private LocalDateTime bookedAt;
	private String bookedBy;
	
	public Phone() {
	}

	public Phone(String model) {
		this.model = model;
		this.isAvailable = true;
	}

	@Override
	public String toString() {
		return "Phone [model=" + model + ", isAvailable=" + isAvailable + ", bookedAt=" + bookedAt + ", bookedBy="
				+ bookedBy + "]";
	}
	
	// Getters and setters:
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public LocalDateTime getBookedAt() {
		return bookedAt;
	}

	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}
}
