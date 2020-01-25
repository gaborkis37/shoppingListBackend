package com.shoppinglist.backend.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppinglist.backend.model.User;
import com.shoppinglist.backend.service.MyUserDetailsService;

@RestController
public class UserController {
	@Autowired
	private MyUserDetailsService userService;

	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping(value = "/registration", produces = "application/json")
	public void addUser(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		userService.registerUser(user);
	}

	@RequestMapping(value = "/activation/{code}", method = RequestMethod.GET, produces = "application/json")
	public void activation(@PathVariable("code") String code, HttpServletResponse response) {
		userService.userActivation(code);
	}
}
