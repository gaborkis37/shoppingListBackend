package com.shoppinglist.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/getProducts", produces = "application/json")
	public List<Product> getProducts(Principal principal) {
		return productService.getProducts(principal.getName());
	}

	@RequestMapping(method = RequestMethod.GET, value = "getProducts/{id}", produces = "application/json")
	public Product findOne(@PathVariable(value = "id") long id) {
		return productService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addProduct", produces = "application/json")
	public List<Product> saveProduct(@RequestBody Product product, Principal principal) {
		productService.addProduct(product,principal.getName());
		return productService.getProducts(principal.getName());
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteProduct/{id}", produces = "application/json")
	public List<Product> deleteProduct(@PathVariable("id") long id, Principal principal) {
		productService.deleteProduct(id);
		return productService.getProducts(principal.getName());
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateProduct/{id}", produces = "application/json")
	public List<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product updateProduct,Principal principal){
		productService.editProduct(id, updateProduct);
		return productService.getProducts(principal.getName());
	}
}
