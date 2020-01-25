package com.shoppinglist.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.shoppinglist.backend.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

	User findByActivation(String activation);
	
}
