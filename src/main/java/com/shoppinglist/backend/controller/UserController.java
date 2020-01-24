package com.shoppinglist.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppinglist.backend.model.User;
import com.shoppinglist.backend.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping(value = "/listUsers", produces = "application/json")
	public List<User> getUsers() {
		return (List<User>) userService.listAllUsers();
	}

	@PostMapping(value = "/addUsers", produces = "application/json")
	void addUser(@RequestBody User user) {
		userService.saveUser(user);
	}
}
