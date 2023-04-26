
package com.example.travelodgeapi.hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.validation.constraints.Size;

@Component
public class HotelCommandRunner implements CommandLineRunner {
	/*
	 * 
	 ******************** JPA *******************
	 * 
	 * @Autowired private HotelJpaRepository hotelJpaRepository;
	 * 
	 * @Override public void run(String... args) throws Exception {
	 * hotelJpaRepository.insert(new Hotel(1, "code22", "Ealing"));
	 * hotelJpaRepository.insert(new Hotel(2, "code33", "Acton"));
	 * hotelJpaRepository.insert(new Hotel(3, "code4", "London"));
	 * 
	 * hotelJpaRepository.deleteById(1L);
	 * 
	 * System.out.println("hotel by ID"+hotelJpaRepository.findById(1));
	 * System.out.println("hotel by ID"+hotelJpaRepository.findById(2));
	 * System.out.println("hotel by ID"+hotelJpaRepository.findById(3));
	 * 
	 * }
	 */

	//////////// spring data JPA
	@Autowired
	private HotelSpringDataJPARepository repository;
	

	@Override
	public void run(String... args) throws Exception {
	/*
		repository.save(new Hotel(1L, "code22", "Ealing", "URL sadwsadas",0.11, 32.2222, LocalDate.parse("2024-01-01")));
		repository.save(new Hotel(2L, "code22", "Ealing", "URL sadwsadas",0.11, 32.2222, LocalDate.parse("2024-01-01")));
		

		repository.save(new Hotel(1L, "code22", "Ealing"));
		repository.save(new Hotel(2L, "code33", "Acton london"));
		repository.save(new Hotel(3L, "code4", "london West"));

		// repository.deleteById(1L);
		System.out.println("xxxxxxxxxxxxxxxxxxx");

		System.out.println("Hotels " + repository.findByTitleContaining("london"));
		repository.save(new Hotel(3, "new 4", "new london West"));
		System.out.println("AllllAAAA" + repository.findAll());
		System.out.println("Count " + repository.count());

		LocalDate date = LocalDate.of(2020, 1, 8);

		LocalTime currentTime = LocalTime.now();
		LocalDateTime fromDateAndTime = LocalDateTime.of(date, currentTime);
		*/
		
	}

}
