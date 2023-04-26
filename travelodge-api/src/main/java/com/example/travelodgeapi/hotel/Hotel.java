package com.example.travelodgeapi.hotel;

import java.time.LocalDate;
import java.util.List;

import com.example.travelodgeapi.price.Price;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Hotel {
	@Id
//	@JsonProperty("id")
	private Long id;
//	@JsonIgnore
//	@JsonProperty("hotelika_codzik")
	@Size(min = 2, max = 10)
//	@Column(name = "hotelCode")
	private String code;

//	@Column(name = "zzzzzzzzzzzz")
//	@JsonProperty("hotel_name")
	// @Size(min = 2, max = 50)
	private String title;
	private String hotelUrl;
	private double latitude;
	private double longitude;
	private LocalDate lastUpdated;

	@OneToMany(mappedBy = "hotel")
	@JsonIgnore
	private List<Price> price;

	public Hotel() {
		super();
	}

	/**
	 * @param id
	 * @param code
	 * @param title
	 * @param hotelUrl
	 * @param latitude
	 * @param longitude
	 * @param lastUpdatedDate
	 * @param price
	 */
	public Hotel(Long id, @Size(min = 2, max = 10) String code, String title, String hotelUrl, double latitude,
			double longitude, LocalDate lastUpdated) {
		super();
		this.id = id;
		this.code = code;
		this.title = title;
		this.hotelUrl = hotelUrl;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lastUpdated = lastUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHotelUrl() {
		return hotelUrl;
	}

	public void setHotelUrl(String hotelUrl) {
		this.hotelUrl = hotelUrl;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public LocalDate getLastUpdatedDate() {
		return lastUpdated;
	}

	public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
		this.lastUpdated = lastUpdatedDate;
	}

	public List<Price> getPrice() {
		return price;
	}

	public void setPrice(List<Price> price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", code=" + code + ", title=" + title + ", hotelUrl=" + hotelUrl + ", latitude="
				+ latitude + ", longitude=" + longitude + ", lastUpdated=" + lastUpdated + ", price=" + price
				+ "]";
	}

}
