package com.example.travelodgeapi.hotel;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
	@Autowired
	private HotelSpringDataJPARepository hotelRepository;

	public Iterable<Hotel> getHotels() {
		return hotelRepository.findAll();
	}

	public void downloadAllHotelsDetails(){
		DownloadHotelDetails.getAllHotelsDetails();			
		hotelRepository.saveAll(DownloadHotelDetails.hotelsDetailsList);
	}
	
	public Optional<Hotel> getHotelByCode(String code) {
		return Optional.ofNullable(hotelRepository.findByCode(code));
	}
	
	

	public Hotel createHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public Hotel updateHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public void deleteHotelById(Long id) {
		hotelRepository.deleteById(id);
	}

	public Optional<Hotel> getHotelById(Long id) {
		return hotelRepository.findById(id);
	}

}
