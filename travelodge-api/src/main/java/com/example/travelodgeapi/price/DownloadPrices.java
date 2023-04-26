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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.AbstractAggregateRoot;

public class DownloadPrices {
	static Map<String, Map<String, String>> hotelPricesMap = new LinkedHashMap<>();
	static List<Price> hotelPricesLiust = new ArrayList<>();

	private static String createUrl(String hotelCode, String date, int batchSize) {
		// max for limit is 11, min is 1
		String urlString = "https://www.travelodge.co.uk/api/v1/hotel/rates?limit=" + String.valueOf(batchSize)
				+ "&hotelCode=" + hotelCode + "&checkIn=" + date
				+ "&nights=1&rooms[0][adults]=1&rooms[0][children]=0&rooms[0][accessible]=false&otherDates=1";
		return urlString;
	}

	private static JsonNode extractPricesResponse(String jsonString)
			throws JsonMappingException, JsonProcessingException {
//		System.out.println("pricessss");
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(jsonString);
		JsonNode jsonPrices = jsonNode.get("data").get("rates");
//		System.out.println("price batch size: " + jsonPrices.size());
		return jsonPrices;

	}

	private static void buildPricesMapFromOneResponse(JsonNode jsonPrices, Hotel hotel) {
//		System.out.println(jsonPrices);
		for (JsonNode jsonPrice : jsonPrices) {
			// System.out.println(jsonPrice);

			Map<String, String> hotelPriceMap = new LinkedHashMap<>();
			Price price = new Price();
			String availabilityStatus = jsonPrice.get("availabilityStatus").toString();
			if ( "\"available\"".equals(availabilityStatus)) {
				hotelPriceMap.put("price", jsonPrice.get("price").get("amount").toString());
				price.setAvailabilityStatus(true);
				price.setPrice(BigDecimal.valueOf(jsonPrice.get("price").get("amount").asDouble()));
			} else {
				price.setAvailabilityStatus(false);
				price.setPrice(BigDecimal.valueOf(-1));

			}
			hotelPriceMap.put("availabilityStatus", jsonPrice.get("availabilityStatus").toString());

			String url = jsonPrice.get("url").toString();
			url = url.substring(1, url.length()-1);
			
			hotelPriceMap.put("url", url);
			price.setUrl(url);

			hotelPriceMap.put("updatedDate", getUTCdate().toString());
			hotelPriceMap.put("updatedTime", getUTCTime().toString());
			
			String date = jsonPrice.get("date").toString();
			date = date.substring(1, date.length()-1);
			
			price.setDate(LocalDate.parse(date));
			price.setHotel(hotel);
			
			price.setLastUpdated(LocalDateTime.now(ZoneOffset.UTC).withNano(0));

			hotelPricesMap.put(jsonPrice.get("date").toString(), hotelPriceMap);
			hotelPricesLiust.add(price);
		}
	}

	private static void getHotelPrices(Hotel hotel, LocalDate dateFrom, LocalDate dateTo) throws JsonMappingException, JsonProcessingException
			 {
		LocalDate today = getUTCdate();
		LocalDate date = dateFrom;

		// max is 11, min is 1
		// max dateTo is today plus 354
		int batchSize = 11;
//		System.out.println(hotel.getCode());
		String url = createUrl(hotel.getCode(), date.toString(), batchSize);

		JsonNode jsonHotelPrices = extractPricesResponse(getResponse(url));
		buildPricesMapFromOneResponse(jsonHotelPrices, hotel);
		boolean notLastBatch = true;
		while (notLastBatch && (jsonHotelPrices.size() > 0) && dateTo.isAfter(date)) {
			date = date.plusDays(batchSize);
			if (date.isAfter(today.plusDays(354))) {
				date = today.plusDays(354 - 5);
				notLastBatch = false;
			}
			url = createUrl(hotel.getCode(), date.toString(), 11);
			jsonHotelPrices = extractPricesResponse(getResponse(url));
			buildPricesMapFromOneResponse(jsonHotelPrices, hotel);
		}
	}

	private static void printHotelPrices(Map<String, Map<String, String>> hotelMap) {
		for (var entry : hotelMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	 static void  downloadPrices(Hotel hotel) throws JsonMappingException, JsonProcessingException {

	//	getHotelPrices(hotel, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-11"));
			getHotelPrices(hotel, LocalDate.now(), LocalDate.parse("2023-05-11"));

	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		String hotelCode = "GB0960";
//		getHotelPrices(hotelCode, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-11"));
		printHotelPrices(hotelPricesMap);
		System.out.println(hotelPricesMap.size());
		System.out.println(LocalDate.parse("2023-04-24").plusDays(354));
		System.out.println(LocalDateTime.now(ZoneOffset.UTC));
	}
}
