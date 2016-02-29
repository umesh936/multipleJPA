package com.umesh.learning.multipleJPA.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.learning.multipleJPA.model.User;
import com.umesh.learning.multipleJPA.services.LoginService;

@RestController
@RequestMapping("/user")
public class LoginController {

	@Resource
	LoginService loginservice;

	@Resource
	ObjectMapper objectMap;

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public Object saveUser(User user) {
		user = loginservice.saveUser(user);
		return user;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public Object getLoginPage(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		User user = loginservice.loginAllowed(email, password);
		return user;
	}

}
