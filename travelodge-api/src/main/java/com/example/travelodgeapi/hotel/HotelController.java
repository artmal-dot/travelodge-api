package com.example.travelodgeapi.hotel;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import com.example.travelodgeapi.oldPrice.OldPrice;
import com.example.travelodgeapi.oldPrice.OldPriceServie;
import com.example.travelodgeapi.price.Price;
import com.example.travelodgeapi.price.PriceService;
import com.example.travelodgeapi.util.Tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.validation.Valid;

// HotelResource
@RestController
//@RequestMapping("/hotels")
public class HotelController {

	private HotelService hotelService;
	private PriceService priceService;
	private OldPriceServie oldPriceService;

	public HotelController(HotelService hotelService, PriceService priceService, OldPriceServie oldPriceService) {
		this.hotelService = hotelService;
		this.priceService = priceService;
		this.oldPriceService = oldPriceService;
	}

	@GetMapping("/hotels")
	public Iterable<Hotel> retrieveAllHotels() {
		return hotelService.getHotels();
	}

	@GetMapping("/hotels/updateAll")
	public Iterable<Hotel> downloadAllHotelsDetails() {
		hotelService.downloadAllHotelsDetails();
		return hotelService.getHotels();
	}

	@GetMapping("/hotels/{hotelId}/livePrices")
	public List<Price> downloadPricesForHotel(@PathVariable long hotelId)
			throws JsonMappingException, JsonProcessingException {
		Optional<Hotel> hotelOptional = hotelService.getHotelById(hotelId);
		if (hotelOptional.isEmpty()) {
			throw new HotelExceptions("Hotel with id:" + hotelId + " doesn't exist");
		}
		Hotel hotel = hotelOptional.get();
		priceService.downloadPrices(hotel);
		return hotelOptional.get().getPrice();
	}

	@GetMapping("/hotels/downloadAllPrices")
	public ResponseEntity<String> downloadAllPricesForAllHotels() throws JsonMappingException, JsonProcessingException {
		long start = System.currentTimeMillis();
		Iterable<Hotel> hotelsList = hotelService.getHotels();
		for (Hotel hotel : hotelsList) {
			priceService.downloadPrices(hotel);
		}
		// create `ObjectMapper` instance
		ObjectMapper mapper = new ObjectMapper();

		// create a JSON object
		ObjectNode message = mapper.createObjectNode();

		message.put("message", "All prices for all hotels has been updated in " +  (System.currentTimeMillis()-start) + " ms" );

		// convert `ObjectNode` to pretty-print JSON
		// without pretty-print, use `user.toString()` method
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<>(json, headers, HttpStatus.OK);

	}

	@GetMapping(value = "/hotels/{id}/prices", produces = "application/json")
	public ResponseEntity<List<Price>> TestgetPricesForHotel(@PathVariable long id,
			@RequestParam Map<String, String> customQuery) {
		Optional<Hotel> hotel = hotelService.getHotelById(id);
		if (hotel.isEmpty()) {
			throw new HotelExceptions("Hotel with id:" + id + " doesn't exist");
		}

		List<Price> prices = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		if (customQuery.containsKey("from") && (customQuery.containsKey("to"))) {
			LocalDate dateFrom = Tools.isDateCorrect(customQuery.get("from"));
			LocalDate dateTo = Tools.isDateCorrect(customQuery.get("to"));

			prices = priceService.getPricesForDates(hotel.get(), dateFrom, dateTo);
			if (!prices.isEmpty()) {
				URI locationUrl = ServletUriComponentsBuilder.fromCurrentRequest().path("")
						.buildAndExpand(hotel.get().getId()).toUri();
				headers.setLocation(locationUrl);
			}
		} else
			throw new HotelExceptions("Please provide dates in query");
		return new ResponseEntity<>(prices, headers, HttpStatus.OK);
	}

	@GetMapping(value = "/hotels/{id}/priceHistory", produces = "application/json")
	public ResponseEntity<List<OldPrice>> getOldPricesForHotel(@PathVariable long id,
			@RequestParam Map<String, String> customerDate) {

		Optional<Hotel> hotel = hotelService.getHotelById(id);
		if (hotel.isEmpty()) {
			throw new HotelExceptions("Hotel with id:" + id + " doesn't exist");
		}
		LocalDate date = Tools.isDateCorrect(customerDate.get("date"));
		List<Price> prices = priceService.getPricesForDates(hotel.get(), date, date);
		if (prices.isEmpty()) {
			throw new HotelExceptions("Current price for hotel_id " + id + " for " + date + " doesn't exist");
		}
		if (prices.size() > 1) {
			throw new HotelExceptions("Error: more then one current price for Hotel with id:" + id + " for " + date);
		}

		Price price = priceService.getPricesForDates(hotel.get(), date, date).get(0);
		List<OldPrice> oldPrices = oldPriceService.getOldPricesForDate(price);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<>(oldPrices, headers, HttpStatus.OK);
	}

	// returns all prices from DB
	@GetMapping("/hotels/{id}")
	public EntityModel<Hotel> retrieveHotel(@PathVariable long id) {
		Optional<Hotel> hotel = hotelService.getHotelById(id);
		if (hotel.isEmpty()) {
			throw new HotelExceptions("Hotel with id:" + id + " doesn't exist");
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

	@GetMapping("/hotels/{id}/price")
	public List<Price> getPricesForHotel(@PathVariable long id) {
		Optional<Hotel> hotel = hotelService.getHotelById(id);
		if (hotel.isEmpty()) {
			throw new HotelExceptions("Hotel with id:" + id + " doesn't exist");
		}
		return hotel.get().getPrice();
	}

	@PostMapping("/hotels/{id}/allPrices")
	public ResponseEntity<Object> createPriceForHotel(@PathVariable long id, @Valid @RequestBody Price price) {
		Optional<Hotel> hotel = hotelService.getHotelById(id);
		if (hotel.isEmpty()) {
			throw new HotelExceptions("Hotel with id:" + id + " doesn't exist");
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
