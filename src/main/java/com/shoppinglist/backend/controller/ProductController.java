package com.shoppinglist.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppinglist.backend.model.Product;
import com.shoppinglist.backend.service.ProductService;

@RestController
public class ProductController {
	
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@RequestMapping(value = "/getProducts", produces="application/json")
	public List<Product> getProducts(Principal principal){
		return productService.getProducts(principal.getName());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addProduct", produces="application/json")
	public Object saveProduct(@RequestParam("name") String name,@RequestParam("price") int price, Principal principal) {
		productService.addProduct(name, price, principal.getName());
		return "{\"Message\":\"Success\"}";
	}
}
