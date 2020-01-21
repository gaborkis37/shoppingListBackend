package com.shoppinglist.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppinglist.backend.model.Product;
import com.shoppinglist.backend.model.User;
import com.shoppinglist.backend.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepo;

	@Autowired
	public ProductService(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}

	public Iterable<Product> listAllProducts() {
		return productRepo.findAll();
	}

	public Iterable<Product> listAllUsersProduct(User user) {
		return productRepo.findByUser(user);
	}

}
