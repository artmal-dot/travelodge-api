package com.example.travelodgeapi.oldPrice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.travelodgeapi.price.Price;

public interface OldPriceSpringDataJPARepository extends JpaRepository<OldPrice, Long> {

	public List<OldPrice> findAllByCurrentPrice(@Param("CurrentPrice") Price price);
	
}