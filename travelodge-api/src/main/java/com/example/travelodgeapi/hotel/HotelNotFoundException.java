package com.example.travelodgeapi.hotel;

import javax.swing.LayoutFocusTraversalPolicy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException {


	public HotelNotFoundException(String message) {
		super(message);
	}
	
	
	
	

}
