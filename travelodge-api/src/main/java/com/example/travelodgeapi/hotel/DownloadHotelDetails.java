package com.example.travelodgeapi.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.travelodgeapi.util.Tools.*;

@Configuration
public class DownloadHotelDetails {

	private List<Hotel> hotelsDetailsList = Collections.synchronizedList(new ArrayList<Hotel>());
	static final int MAX_NUMBER_HOTELS = 200;

	public List<Hotel> getHotelsDetailsList() {
		return hotelsDetailsList;
	}

	private JsonNode extractHotelDetailsFromResponse(String jsonString)
			throws JsonProcessingException, JsonMappingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(jsonString);
		JsonNode jsonHotels = jsonNode.get("results");
		return jsonHotels;
	}

	private void addHotelsToList(JsonNode jsonHotels) {
		LocalDate UTCdate = getUTCdate();
		for (JsonNode jsonHotel : jsonHotels) {
			Hotel hotel = new Hotel();

			String urlString = jsonHotel.get("hotelUrl").toString();

			urlString = urlString.substring(1, urlString.indexOf("?"));
			hotel.setHotelUrl(urlString);

			String hotelCode = jsonHotel.get("code").toString();
			hotelCode = hotelCode.substring(1, hotelCode.length() - 1);
			hotel.setCode(hotelCode);

			String title = jsonHotel.get("title").toString();
			title = title.substring(1, title.length() - 1);
			hotel.setTitle(title);

			hotel.setLatitude(Double.valueOf(jsonHotel.get("lat").toString()));

			hotel.setLongitude(Double.valueOf(jsonHotel.get("lon").toString()));

			hotel.setLastUpdatedDate(UTCdate);

			hotel.setId(jsonHotel.get("id").longValue());
			if (hotelsDetailsList.size() < MAX_NUMBER_HOTELS)
				hotelsDetailsList.add(hotel);
		}
	}

	private void printAllKeys(JsonNode jsonHotels) {
		JsonNode jsonHotel = jsonHotels.get(0);
		System.out.println(jsonHotels.get(0).size());
		List<String> keys = new ArrayList<>();
		Iterator<String> iterator = jsonHotel.fieldNames();
		iterator.forEachRemaining(e -> keys.add(e));
		System.out.println(keys);
	}

	private void createHotelsList() throws JsonProcessingException, JsonMappingException {
		final String url = createUrl();
		// max =10
		int maxBatch = 10;
		JsonNode jsonHotels = extractHotelDetailsFromResponse(getResponse(url + String.valueOf(maxBatch)));
//		System.out.println("after 1st extracting");

		int start = 0;
		while (jsonHotels.size() > 0) {
			start += 10;
			jsonHotels = extractHotelDetailsFromResponse(getResponse(url + String.valueOf(start)));
			addHotelsToList(jsonHotels);
		}
	}

	private String createUrl() {
		// example
		// "https://www.travelodge.co.uk/api/v2/hotel?checkIn=2023-04-29&checkOut=2023-04-30&q=london&rooms[0][adults]=1&rooms[0][children]=0&sb=0&start=";
		final String url = "https://www.travelodge.co.uk/api/v2/hotel?checkIn=" + getUTCdate().plusMonths(1)
				+ "&checkOut=" + getUTCdate().plusDays(1).plusMonths(1)
				+ "&q=london&rooms[0][adults]=1&rooms[0][children]=0&sb=0&start=";
		// + "&q=ealing&rooms[0][adults]=1&rooms[0][children]=0&sb=0&start=";
		return url;
	}

	public void downloadAllHotelsDetails() {
		try {
			createHotelsList();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}