package com.shoppinglist.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppinglist.backend.model.Product;
import com.shoppinglist.backend.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepo;

	@Autowired
	public ProductService(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}

	public List<Product> getProducts(String holdername) {
		return productRepo.findByHolderName(holdername);
	}

	public Product findOne(Long id) {
		return productRepo.findById(id).orElseThrow(() -> new IllegalStateException("Product not found"));
	}

	public void addProduct(String name, int price, String holderName) {
		productRepo.save(new Product(name, price, holderName));
	}

	public void deleteProduct(long id) {
		productRepo.deleteById(id);
	}

	public void editProduct(long id, Product update) {
		Product product = productRepo.findById(id).get();
		if (product.getName() != null) {
			product.setName(update.getName());
		}
		product.setPrice(update.getPrice());
		productRepo.save(product);
	}

}
