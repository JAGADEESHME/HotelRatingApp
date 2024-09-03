package com.mycoding.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mycoding.user.service.entity.Hotel;
import com.mycoding.user.service.entity.Rating;
import com.mycoding.user.service.entity.User;
import com.mycoding.user.service.exceptions.ResourceNotFoundException;
import com.mycoding.user.service.external.services.HotelService;
import com.mycoding.user.service.repositories.UserRepository;
import com.mycoding.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {

		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server" + userId));
		// fetch rating of the above user from RATING SERVICE
		// http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICES/ratings/users/" + user.getUserId(),
				Rating[].class);
		logger.info("{} ", ratingsOfUser);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			// http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
			// ResponseEntity<Hotel> forEntity =
			// restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),
			// Hotel.class);
			// Hotel hotel = forEntity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// logger.info("response status code: {} ", forEntity.getStatusCode());
			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);
		return user;
	}

}
