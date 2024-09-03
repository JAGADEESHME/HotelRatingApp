package com.mycoding.rating.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mycoding.rating.entities.Rating;
import java.util.List;


public interface RatingRepository extends MongoRepository<Rating, String> {
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);

}
