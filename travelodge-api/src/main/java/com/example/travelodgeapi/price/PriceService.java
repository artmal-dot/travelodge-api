package com.example.travelodgeapi.price;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelodgeapi.hotel.Hotel;

@Service
public class PriceService {
	@Autowired
	private PriceSpringDataJPARepository priceRepository;
	
	public Price creatPrice(Price price) {
		return priceRepository.save(price);
	}

	
}
