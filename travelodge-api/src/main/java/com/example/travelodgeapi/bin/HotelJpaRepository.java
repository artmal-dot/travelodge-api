 package com.example.travelodgeapi.bin;

import org.springframework.stereotype.Repository;

import com.example.travelodgeapi.hotel.Hotel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class HotelJpaRepository {
//	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	public void insert(Hotel hotel) {
		entityManager.merge(hotel);
	}

	public Hotel findById(long id) {
		return entityManager.find(Hotel.class, id);
	}
	
	public void deleteById(Long id) {
		Hotel hotel = entityManager.find(Hotel.class, id);
		entityManager.remove(hotel);
	}

}
