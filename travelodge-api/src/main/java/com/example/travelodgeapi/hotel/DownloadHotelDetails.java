package com.example.travelodgeapi.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.travelodgeapi.util.Tools.*;

public class DownloadHotelDetails {
	static Map<Integer, Map<String, String>> hotelsDetailsMap = new HashMap<>();
	static List<Hotel> hotelsDetailsList = Collections.synchronizedList(new ArrayList<Hotel>());
	static final int MAX_NUMBER_HOTELS = 200;

	private static JsonNode extractHotelDetailsFromResponse(String jsonString)
			throws JsonProcessingException, JsonMappingException {
//		System.out.println("zzzzzzzzzzzzzzzzzzzzzz");
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(jsonString);
		JsonNode jsonHotels = jsonNode.get("results");
//		System.out.println("Size: "+jsonHotels.size());
		return jsonHotels;
	}

	private static void addHotelsToMap(JsonNode jsonHotels, Map<Integer, Map<String, String>> hotelMap) {
		LocalDate UTCdate = getUTCdate();
		for (JsonNode jsonHotel : jsonHotels) {
			
			//System.out.println(jsonHotel);
			//System.out.println();
			Hotel hotel = new Hotel(); 
			
			Map<String, String> hotelDetailsMap = new HashMap<>();
			String urlString = jsonHotel.get("hotelUrl").toString();

			urlString = urlString.substring(1, urlString.indexOf("?"));
			hotelDetailsMap.put("hotelUrl", urlString);
			hotel.setHotelUrl(urlString);
			
			
			String hotelCode = jsonHotel.get("code").toString();
			hotelCode = hotelCode.substring(1, hotelCode.length()-1);
			hotelDetailsMap.put("code", hotelCode);
			hotel.setCode(hotelCode);
			
			String title = jsonHotel.get("title").toString();
			title = title.substring(1, title.length()-1);
			hotelDetailsMap.put("title", title);
			hotel.setTitle(title);
			
			hotelDetailsMap.put("lat", jsonHotel.get("lat").toString());
			hotel.setLatitude(Double.valueOf(jsonHotel.get("lat").toString()));
			
			hotelDetailsMap.put("lon", jsonHotel.get("lon").toString());
			hotel.setLongitude(Double.valueOf(jsonHotel.get("lon").toString()));
			
			hotelDetailsMap.put("updated", UTCdate.toString());
			hotel.setLastUpdatedDate(UTCdate);


				hotelMap.put(jsonHotel.get("id").intValue(), hotelDetailsMap);

			hotel.setId(jsonHotel.get("id").longValue());
			if (hotelsDetailsList.size()<MAX_NUMBER_HOTELS) 
			hotelsDetailsList.add(hotel);
		}
	}

	private static void printHotelMap(Map<Integer, Map<String, String>> hotelMap) {
		for (var entry : hotelMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}

	private static void printAllKeys(JsonNode jsonHotels) {
		JsonNode jsonHotel = jsonHotels.get(0);
		System.out.println(jsonHotels.get(0).size());
		List<String> keys = new ArrayList<>();
		Iterator<String> iterator = jsonHotel.fieldNames();
		iterator.forEachRemaining(e -> keys.add(e));
		System.out.println(keys);
	}

	private static void createHotelsDetailMap() throws JsonProcessingException, JsonMappingException {
		final String url = createUrl();
//		List<String> keys = new ArrayList<>();
//		ObjectMapper objectMapper = new ObjectMapper();
//		JsonNode jsonNode = objectMapper.readTree(jsonString);
//		Iterator<String> iterator = jsonNode.fieldNames();
//		iterator.forEachRemaining(e -> keys.add(e));
//		System.out.println(keys);
//		JsonNode jsonHotels = extractHotelDetailsFromResponse(getResponse(url + "0"));
		//max =10
		int maxBatch =12;
		JsonNode jsonHotels = extractHotelDetailsFromResponse(getResponse(url + String.valueOf(maxBatch)));
//		System.out.println("after 1st extracting");
		addHotelsToMap(jsonHotels, hotelsDetailsMap);
//		System.out.println("after 1st mapping");
//		printAllKeys(jsonHotels);
		
		int start = 0;
		while (jsonHotels.size() > 0 ) {
			start += 10;
			jsonHotels = extractHotelDetailsFromResponse(getResponse(url + String.valueOf(start)));
			addHotelsToMap(jsonHotels, hotelsDetailsMap);
		}
	}

	private static String createUrl() {
		// example
		// "https://www.travelodge.co.uk/api/v2/hotel?checkIn=2023-04-29&checkOut=2023-04-30&q=london&rooms[0][adults]=1&rooms[0][children]=0&sb=0&start=";
		final String url = "https://www.travelodge.co.uk/api/v2/hotel?checkIn=" + getUTCdate().plusMonths(1)
				+ "&checkOut=" + getUTCdate().plusDays(1).plusMonths(1)
				+ "&q=london&rooms[0][adults]=1&rooms[0][children]=0&sb=0&start=";
		//		+ "&q=ealing&rooms[0][adults]=1&rooms[0][children]=0&sb=0&start=";
		return url;
	}
	public static void getAllHotelsDetails(){
		try {
			createHotelsDetailMap();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		createHotelsDetailMap();
		
		printHotelMap(hotelsDetailsMap);
		System.out.println(hotelsDetailsMap.size());
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjj");
		System.out.println(hotelsDetailsList);

	}

}