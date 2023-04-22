package com.example.travelodgeapi.hotel;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.apache.catalina.User;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.travelodgeapi.price.Price;
import com.example.travelodgeapi.price.PriceService;

import jakarta.validation.Valid;

// HotelResource
@RestController
//@RequestMapping("/hotels")
public class HotelController {

	private HotelService hotelService;
	private PriceService priceService;

	public HotelController(HotelService hotelService, PriceService priceService) {

		this.hotelService = hotelService;
		this.priceService = priceService;
	}

	@GetMapping("/hotels")
	public Iterable<Hotel> retrieveAllHotels() {
		return hotelService.getHotels();
	}

	@GetMapping("/hotels/{id}")
	public EntityModel<Hotel> retrieveHotel(@PathVariable long id) {
		Optional<Hotel> hotel = hotelService.getHotelById(id);
		if (hotel.isEmpty()) {
			throw new HotelNotFoundException("Hotel with id:" + id + " doesn't exist");
		}
		EntityModel<Hotel> entityModel = EntityModel.of(hotel.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllHotels());
		entityModel.add(link.withRel("all-hotels"));
		return entityModel;
	}

	@DeleteMapping("/hotels/{id}")
	public void deleteHotel(@PathVariable long id) {
		hotelService.deleteHotelById(id);
	}

	@GetMapping("/hotels/{id}/prices")
	public List<Price> retrivePricesForHotel(@PathVariable long id) {
		Optional<Hotel> hotel = hotelService.getHotelById(id);
		if (hotel.isEmpty()) {
			throw new HotelNotFoundException("Hotel with id:" + id + " doesn't exist");
		}
		return hotel.get().getPrice();
	}

	@PostMapping("/hotels/{id}/prices")
	public ResponseEntity<Object> createPriceForHotel(@PathVariable long id, @Valid @RequestBody Price price) {
		Optional<Hotel> hotel = hotelService.getHotelById(id);
		if (hotel.isEmpty()) {
			throw new HotelNotFoundException("Hotel with id:" + id + " doesn't exist");
		}
		price.setHotel(hotel.get());
		Price savedPrice = priceService.creatPrice(price);

		URI locationUrl = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPrice.getId()).toUri();
		return ResponseEntity.created(locationUrl).build();

	}

	@PostMapping("/hotels")
	public ResponseEntity<Hotel> createUser(@Valid @RequestBody Hotel hotel) {
		Hotel savedHotel = hotelService.createHotel(hotel);
		URI locationUrl = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedHotel.getId()).toUri();
		return ResponseEntity.created(locationUrl).build();
	}

}
