package com.example.travelodgeapi.price;

import com.example.travelodgeapi.hotel.Hotel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.travelodgeapi.util.Tools.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DownloadPrices {
	static List<Price> hotelPricesList = Collections.synchronizedList(new ArrayList<>());
	static final int NUMBER_OF_WEEKS_AHEAD = 222;

	private static String createUrl(String hotelCode, String date, int batchSize) {
		// max for limit is 11, minimum is 1
		String urlString = "https://www.travelodge.co.uk/api/v1/hotel/rates?limit=" + String.valueOf(batchSize)
				+ "&hotelCode=" + hotelCode + "&checkIn=" + date
				+ "&nights=1&rooms[0][adults]=1&rooms[0][children]=0&rooms[0][accessible]=false&otherDates=1";
		return urlString;
	}

	private static JsonNode extractPricesResponse(String jsonString)
			throws JsonMappingException, JsonProcessingException {
//		System.out.println("---- one response with rates " + jsonString);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(jsonString);
		JsonNode jsonPrices = jsonNode.get("data").get("rates");
//		System.out.println("price batch size: " + jsonPrices.size());
		return jsonPrices;

	}

	private static void buildPricesMapFromOneResponse(JsonNode jsonPrices, Hotel hotel) {
//		System.out.println(jsonPrices);
		for (JsonNode jsonPrice : jsonPrices) {

//			System.out.println(jsonPrice);
			
			Price price = new Price();
			String availabilityStatus = jsonPrice.get("availabilityStatus").toString();
			if ("\"available\"".equals(availabilityStatus)) {

				price.setAvailabilityStatus(true);
				price.setPrice(BigDecimal.valueOf(jsonPrice.get("price").get("amount").asDouble()));
			} else {
				price.setAvailabilityStatus(false);
				price.setPrice(BigDecimal.valueOf(-1));

			}

			String url = jsonPrice.get("url").toString();
			url = url.substring(1, url.length() - 1);

			price.setUrl(url);

			String date = jsonPrice.get("date").toString();
			date = date.substring(1, date.length() - 1);

			price.setDate(LocalDate.parse(date));
			price.setHotel(hotel);

			price.setLastUpdated(LocalDateTime.now(ZoneOffset.UTC).withNano(0));
//			System.out.println(price.isAvailabilityStatus());
			hotelPricesList.add(price);
		}
	}

	private static void getHotelPrices(Hotel hotel, LocalDate dateFrom, LocalDate dateTo)
			throws JsonMappingException, JsonProcessingException {
		LocalDate today = getUTCdate();
		LocalDate date = dateFrom;

		// max is 11, min is 1
		// max dateTo is today plus 354
		int batchSize = 11;

		String url = createUrl(hotel.getCode(), date.toString(), batchSize);

		JsonNode jsonHotelPrices = extractPricesResponse(getResponse(url));
		buildPricesMapFromOneResponse(jsonHotelPrices, hotel);
		boolean notLastBatch = true;
		date = date.plusDays(batchSize + batchSize / 2);
		while (notLastBatch && (jsonHotelPrices.size() > 0)
				&& dateTo.isAfter(date.minusDays(batchSize + batchSize / 2 - 4))) {
			if (date.isAfter(today.plusDays(354))) {
				date = today.plusDays(354 - 5);
				notLastBatch = false;
			}
			url = createUrl(hotel.getCode(), date.toString(), 11);
			jsonHotelPrices = extractPricesResponse(getResponse(url));
			buildPricesMapFromOneResponse(jsonHotelPrices, hotel);
			date = date.plusDays(batchSize);
		}
	}

	static void downloadPrices(Hotel hotel) throws JsonMappingException, JsonProcessingException {
		LocalDate dateFrom = LocalDate.now();
		LocalDate dateTo = LocalDate.now().plusWeeks(NUMBER_OF_WEEKS_AHEAD);
		if (dateTo.isAfter(dateFrom.plusDays(354))) {
			dateTo = dateFrom.plusDays(354);
		}
		System.out.println(LocalDateTime.now().withNano(0) + " Downloading prices for hotel with code "
				+ hotel.getCode() + " ( id: " + hotel.getId() + ")" + "[" + dateFrom + " - " + dateTo + "]");
		hotelPricesList = Collections.synchronizedList(new ArrayList<>());

		getHotelPrices(hotel, dateFrom, dateTo);
		System.out.println(LocalDateTime.now().withNano(0) + " Downloading prices completed"				);
	}
}