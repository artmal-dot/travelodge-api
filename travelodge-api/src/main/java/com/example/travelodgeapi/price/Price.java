package com.example.travelodgeapi.price;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.travelodgeapi.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Price {
	@Id
	@GeneratedValue
	private Long id;
	
	private LocalDate date;
	private BigDecimal price;
	private boolean hasAvailability;
	private String url;
	private LocalDateTime lastUpdated;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Hotel hotel;

	public Price() {
		super();
	}

	/**
	 * @param id
	 * @param date
	 * @param price
	 * @param availabilityStatus
	 * @param hotel
	 */
	public Price(Long id, LocalDate date, BigDecimal price, boolean availabilityStatus, String url,LocalDateTime lastUpdatedDateTime, Hotel hotel) {
		super();
		this.id = id;
		this.date = date;
		this.price = price;
		this.hasAvailability = availabilityStatus;
		this.hotel = hotel;
		this.url = url;
		this.lastUpdated = lastUpdatedDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isAvailabilityStatus() {
		return hasAvailability;
	}

	public void setAvailabilityStatus(boolean availabilityStatus) {
		this.hasAvailability = availabilityStatus;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", date=" + date + ", price=" + price + ", hasAvailability=" + hasAvailability
				+ ", url=" + url + ", lastUpdated=" + lastUpdated + ", hotel=" + hotel + "]";
	}




	

}