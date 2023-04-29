package com.example.travelodgeapi.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.jsoup.Jsoup;

import com.example.travelodgeapi.hotel.HotelExceptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public final class Tools {
	static final String CUSTOM_PATTERN = "yyyy-MM-dd";
	static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(CUSTOM_PATTERN);
	
	public static LocalDate getUTCdate() {
		Instant instant = Instant.now();
		LocalDate date = instant.atZone(ZoneOffset.UTC).toLocalDate();
		return date;
	}

	public static LocalTime getUTCTime() {
		Instant instant = Instant.now();
		LocalTime time = instant.atZone(ZoneOffset.UTC).toLocalTime();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedTime = time.format(timeFormatter);
		LocalTime UTCtime = LocalTime.parse(formattedTime, timeFormatter);
		return UTCtime;
	}
	
	public static LocalDate isValidLocalDate(String dateStr) {
		LocalDate date = null;
		try {
			date = LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
		} catch (DateTimeParseException e) {
			// handle exception
			e.printStackTrace();
		}
		return date;
	}

	public static String getResponse(String url) {
		String json = null;
		try {
			json = Jsoup.connect(url).ignoreContentType(true).execute().body();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static LocalDate isDateCorrect (String dateString ) {
		LocalDate date;
		try {
			 date = LocalDate.parse(dateString);
		} catch (Exception e) {
			throw new HotelExceptions("Date " + dateString + " is not correct. Correct format is YYYY-MM-DD");
		}	
		return date;
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
	System.out.println(isValidLocalDate("2022-01-10"));

	}
}
