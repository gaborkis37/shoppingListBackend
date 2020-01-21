package com.shoppinglist.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppinglist.backend.model.User;
import com.shoppinglist.backend.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	
	public Iterable<User> listAllUsers() {
		return userRepo.findAll();
	}
	
	public Optional<User> findOneUser(Long id) {
		return userRepo.findById(id);
	}

	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	
	
}
