package com.example.travelodgeapi.price;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelodgeapi.hotel.Hotel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class PriceService {
	@Autowired
	private PriceSpringDataJPARepository priceRepository;

	public Price creatPrice(Price price) {
		return priceRepository.save(price);
	}

	public void downloadPrices(Hotel hotel) throws JsonMappingException, JsonProcessingException {
		DownloadPrices.downloadPrices(hotel);
		priceRepository.saveAll(DownloadPrices.hotelPricesLiust);
	}

	public List<Price> getPriceForDate(Hotel hotel, LocalDate dateFrom, LocalDate dateTo) {
		List<Price> prices = new LinkedList<>();
		prices = priceRepository.findByHotelAndDateBetweenOrderByDate(hotel, dateFrom, dateTo);
		return prices;
	}

}