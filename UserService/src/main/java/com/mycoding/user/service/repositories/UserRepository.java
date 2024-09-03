package com.mycoding.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycoding.user.service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
