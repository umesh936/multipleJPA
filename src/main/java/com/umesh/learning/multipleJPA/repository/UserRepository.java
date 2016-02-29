package com.umesh.learning.multipleJPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umesh.learning.multipleJPA.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByEmailAndPassword(String email, String password);
}
