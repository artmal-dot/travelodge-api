package com.example.travelodgeapi.oldPrice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelodgeapi.price.Price;

@Service
public class OldPriceServie {

	@Autowired
	private OldPriceSpringDataJPARepository oldPriceSpringDataJPARepository;
	
	public List<OldPrice>getOldPricesForDate(Price curentPrice){
		return oldPriceSpringDataJPARepository.findAllByCurrentPrice(curentPrice);
	}

}
