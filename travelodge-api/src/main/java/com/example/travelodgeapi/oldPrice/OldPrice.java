package com.example.travelodgeapi.oldPrice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.travelodgeapi.hotel.Hotel;
import com.example.travelodgeapi.price.Price;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OldPrice {

	@Id
	@GeneratedValue
	private Long id;
	private BigDecimal price;
	private LocalDateTime fetchDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Price currentPrice;

	public OldPrice() {
		super();
	}

	public OldPrice(Long id, BigDecimal price, LocalDateTime fetchDateTime, Price currentPrice) {
		super();
		this.id = id;
		this.price = price;
		this.fetchDateTime = fetchDateTime;
		this.currentPrice = currentPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Price getCurrentPrice() { 
		return currentPrice;
	}

	public void setCurrentPrice(Price currentPrice) {
		this.currentPrice = currentPrice;
	}

	public LocalDateTime getFetchDateTime() {
		return fetchDateTime;
	}

	public void setFetchDateTime(LocalDateTime fetchDateTime) { 
		this.fetchDateTime = fetchDateTime;
	}

	@Override
	public String toString() {
		return "OldPrice [id=" + id + ", price=" + price + ", fetchDateTime=" + fetchDateTime + ", currentPrice="
				+ currentPrice + "]";
	}
}