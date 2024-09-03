package com.mycoding.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycoding.hotel.entities.Hotel;
import com.mycoding.hotel.exceptions.ResourceNotFoundException;
import com.mycoding.hotel.repositories.HotelRepository;
import com.mycoding.hotel.services.HotelService;


@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomUserId = UUID.randomUUID().toString();
		hotel.setId(randomUserId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not found on server"+id));
	}

}
