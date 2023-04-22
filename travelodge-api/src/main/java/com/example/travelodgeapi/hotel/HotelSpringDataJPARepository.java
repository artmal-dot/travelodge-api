package com.example.travelodgeapi.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelSpringDataJPARepository extends JpaRepository<Hotel, Long> {

	List<Hotel> findByTitleContaining(String title);

}
