package com.example.travelodgeapi.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {
	private LocalDateTime timeStampDate;
	private String message;
	String details;
	/**
	 * @param timeStampDate
	 * @param message
	 * @param details
	 */
	public ErrorDetails(LocalDateTime timeStampDate, String message, String details) {
		super();
		this.timeStampDate = timeStampDate;
		this.message = message;
		this.details = details;
	}
	public LocalDateTime getTimeStampDate() {
		return timeStampDate;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	
	

}
