package com.example.travelodgeapi.hotel;

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
	@JsonProperty("hotel_id")
	private Long id;
//	@JsonIgnore
//	@JsonProperty("hotelika_codzik")
	@Size(min = 2, max = 10)
//	@Column(name = "hotelCode")
	private String hotelCode;

//	@Column(name = "zzzzzzzzzzzz")
	@JsonProperty("hotel_name")
	@Size(min = 2, max = 50)
	private String title;
	
	@OneToMany(mappedBy = "hotel" )
	@JsonIgnore
	private List<Price> price;
	
//	private String hotelUrl;
//	private String mainImage;
//	private double lat;
//	private double lon;
//	private boolean isOpeningSoon;
//	private LocalDate openingDate;
//	private boolean hasAvailability;
//	private boolean hasLowAvailability;
//	private LocalDate fetchedDate;

	public Hotel(long id, String hotelCode, String title) {
		super();
		this.id = id;
		this.hotelCode = hotelCode;
		this.title = title;
	}
	public Hotel() {
		super();	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Price> getPrice() {
		return price;
	}
	public void setPrice(List<Price> price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", hotelCode=" + hotelCode + ", title=" + title + "]";
	}
}
