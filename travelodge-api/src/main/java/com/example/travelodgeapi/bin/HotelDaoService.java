package com.example.travelodgeapi.bin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.travelodgeapi.hotel.Hotel;

@Component
public class HotelDaoService {

	private static List<Hotel> hotels = new ArrayList<>();

	static {
		hotels.add(new Hotel(1, "aaa1", "London Ealing"));
		hotels.add(new Hotel(2, "aaa2", "Hanslow  London"));
		hotels.add(new Hotel(3, "aaa3", "London Acton"));
	}

	public List<Hotel> findAll() {
		return hotels;
	}

	public Hotel findOne(int id) {
		Predicate<? super Hotel> predicate = hotel -> hotel.getId().equals(id);
		return hotels.stream().filter(predicate).findFirst().orElse(null);
	}

	public List<Hotel> findAllWithName(String name) {

		List<Hotel> result = hotels.stream().filter(p -> p.getTitle().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
		return result;
	}

	public void deleteById(int id) {
		Predicate<? super Hotel> predicate = hotel -> hotel.getId().equals(id);
		hotels.removeIf(predicate);
	}
}
