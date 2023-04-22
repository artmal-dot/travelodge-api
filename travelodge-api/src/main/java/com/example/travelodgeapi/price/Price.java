package com.example.travelodgeapi.price;

import java.math.BigDecimal;
import java.time.LocalDate;

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
//	private LocalDateTime fetched;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Hotel hotel;

	/**
	 * @param id
	 * @param date
	 * @param price
	 * @param hotel
	 */
	public Price(Long id, LocalDate date, BigDecimal price, Hotel hotel) {
		super();
		this.id = id;
		this.date = date;
		this.price = price;
		this.hotel = hotel;
	}

	/**
	 * 
	 */
	public Price() {
		super();
	}

	/**
	 * @param id
	 * @param date
	 * @param price
	 */
	public Price(Long id, LocalDate date, BigDecimal price) {
		super();
		this.id = id;
		this.date = date;
		this.price = price;
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", date=" + date + ", price=" + price + "]";
	}
}
