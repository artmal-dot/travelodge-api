package com.example.travelodgeapi.price;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.travelodgeapi.hotel.Hotel;
import com.example.travelodgeapi.oldPrice.OldPrice;

public interface PriceSpringDataJPARepository extends JpaRepository<Price, Long> {

	public List<Price> findByHotelAndDateBetweenOrderByDate(@Param("hotel") Hotel hotel,
			@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

//	public Price findByHotelAndDate(@Param("hotel") Hotel hotel,
//			@Param("date") LocalDate date);
	
	public List<Price> getAllByHotel(Hotel hotel);
}