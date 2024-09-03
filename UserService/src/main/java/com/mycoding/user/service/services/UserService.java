package com.mycoding.user.service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycoding.user.service.entity.User;

@Service
public interface UserService {
	//create
	User saveUser(User user);
	
	//fetch all user
	List<User> getAllUser();
	
	//get single user
	User getUser(String User);

}
