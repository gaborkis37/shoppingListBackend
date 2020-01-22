package com.shoppinglist.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppinglist.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByHolderName(String holderName);
	
}
