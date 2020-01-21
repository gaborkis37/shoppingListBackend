package com.shoppinglist.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppinglist.backend.model.Product;
import com.shoppinglist.backend.model.User;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByUser(User user);
	
}
