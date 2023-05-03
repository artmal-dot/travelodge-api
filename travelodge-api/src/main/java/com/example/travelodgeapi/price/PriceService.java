package com.example.travelodgeapi.price;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelodgeapi.hotel.Hotel;
import com.example.travelodgeapi.hotel.HotelExceptions;
import com.example.travelodgeapi.oldPrice.OldPrice;
import com.example.travelodgeapi.oldPrice.OldPriceServie;
import com.example.travelodgeapi.oldPrice.OldPriceSpringDataJPARepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class PriceService {
	@Autowired
	private PriceSpringDataJPARepository priceRepository;
	@Autowired
	private OldPriceSpringDataJPARepository oldPriceRepository;

	public Price creatPrice(Price price) {
		return priceRepository.save(price);
	}

	public void downloadPrices(Hotel hotel) throws JsonMappingException, JsonProcessingException {
		/*
		 * if downloaded price is different (lower or higher) then price in Price: -
		 * PRICE and LAST_UPDATED in the Price table will be copied to PRICE and
		 * fetched_date in Old_price - current_price and LAST_UPDATED will be updated
		 * 
		 * if downloaded price is the same as the price in Price: - only LAST_UPDATED in
		 * PRICE will be updated
		 * 
		 */
		DownloadPrices.downloadPrices(hotel);
		List<Price> dowloadedPricesForHotel = Collections.synchronizedList(new ArrayList<>());
		dowloadedPricesForHotel = DownloadPrices.createHotelPricesList;

		for (Price downloadedPrice : dowloadedPricesForHotel) {

			List<Price> currentPriceForDate = priceRepository.findByHotelAndDateBetweenOrderByDate(hotel,
					downloadedPrice.getDate(), downloadedPrice.getDate());
			if (currentPriceForDate.isEmpty()) {
				priceRepository.save(downloadedPrice);
				priceRepository.flush();
			} else if (currentPriceForDate.size() > 1) {
				throw new HotelExceptions("Fore then 1 current price for the date"+hotel.getId());
			} else {
				Price currentPrice = currentPriceForDate.get(0);
				if (currentPrice.getPrice().compareTo(downloadedPrice.getPrice()) == 0) {
					currentPrice.setLastUpdated(downloadedPrice.getLastUpdated());
					priceRepository.save(currentPrice);
//					priceRepository.flush();
				} else {
					OldPrice oldPrice = new OldPrice();
					oldPrice.setCurrentPrice(currentPrice);
					oldPrice.setFetchDateTime(currentPrice.getLastUpdated());
					oldPrice.setPrice(currentPrice.getPrice());
					oldPriceRepository.save(oldPrice);

					currentPrice.setAvailabilityStatus(downloadedPrice.isAvailabilityStatus());
					currentPrice.setDate(downloadedPrice.getDate());
					currentPrice.setLastUpdated(downloadedPrice.getLastUpdated());
					currentPrice.setPrice(downloadedPrice.getPrice());
					priceRepository.save(currentPrice);
//					priceRepository.flush();
				}
			}
		}
//		priceRepository.saveAll(DownloadPrices.createHotelPricesList);
	}

	public void downloadPricesOld(Hotel hotel) throws JsonMappingException, JsonProcessingException {
		DownloadPrices.downloadPrices(hotel);
		priceRepository.saveAll(DownloadPrices.createHotelPricesList);
	}

	public List<Price> getPricesForDates(Hotel hotel, LocalDate dateFrom, LocalDate dateTo) {
		List<Price> prices = new LinkedList<>();
		prices = priceRepository.findByHotelAndDateBetweenOrderByDate(hotel, dateFrom, dateTo);
		return prices;
	}
}