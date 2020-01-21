package com.shoppinglist.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shoppinglist.backend.model.Product;
import com.shoppinglist.backend.model.User;
import com.shoppinglist.backend.service.ProductService;
import com.shoppinglist.backend.service.UserService;

@RestController
public class ProductController {
	
	private ProductService productService;
	private UserService userService;

	@Autowired
	public ProductController(ProductService productService, UserService userService) {
		super();
		this.productService = productService;
		this.userService = userService;
	}
	
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public Iterable<Product> products() {
		return productService.listAllProducts();
	}
	
	@RequestMapping(value = "/userproducts/{id}", method = RequestMethod.GET)
	public Iterable<Product> usersProducts(@PathVariable(value="id") long id) {
		User user = userService.findOneUser(id).orElse(null);
		return productService.listAllUsersProduct(user);
	}
}
