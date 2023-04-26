package com.example.travelodgeapi.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;

public final class Tools {

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
	public static String getResponse(String url) {
		String json = null;
		try {
			json = Jsoup.connect(url).ignoreContentType(true).execute().body();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
