package com.umesh.learning.multipleJPA.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umesh.learning.multipleJPA.model.user.User;
import com.umesh.learning.multipleJPA.repository.user.UserRepository;

@Service
@Transactional(value= "userTransactionManager")
public class LoginService {

	@Resource
	UserRepository userRepo;

	public User loginAllowed(String email, String password) {
		List<User> usersList = userRepo.findByEmailAndPassword(email, password);
		if (usersList.size() == 1) {
			User user = usersList.get(0);
			return user;
		}
		return null;

	}

	public User saveUser(User user) {
		user = userRepo.saveAndFlush(user);
		return user;
	}

}
