package com.mycoding.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycoding.user.service.entity.Hotel;

@FeignClient(name = "HOTEL-SERVICES")
public interface HotelService {
	
	 @GetMapping("/hotels/{hotelId}")
	 Hotel getHotel(@PathVariable("hotelId") String hotelId);

}
