package com.mycoding.hotel.services;

import java.util.List;

import com.mycoding.hotel.entities.Hotel;

public interface HotelService {
	
	//create
	Hotel createHotel(Hotel hotel);
	
	//getall
	List<Hotel> getAll();
	
	//fetch hotel by ID
	Hotel get(String id);

}
