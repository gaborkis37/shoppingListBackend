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
		return productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
	}

	public void addProduct(Product product, String holdername) {
		if (productIsNotEmpty(product)) {
			product.setHolderName(holdername);
			productRepo.save(product);
		}
	}

	private boolean productIsNotEmpty(Product product) {
		return product.getName() != null && priceIsNotZero(product);
	}

	public void deleteProduct(long id) {
		productRepo.deleteById(id);
	}

	public Product editProduct(long id, Product update) {
		Product product = productRepo.findById(id).get();
		if (usernameIsNotEmpty(update)) {
			product.setName(update.getName());
		}
		if (priceIsNotZero(update)) {
			product.setPrice(update.getPrice());
		}

		return productRepo.save(product);
	}

	private boolean priceIsNotZero(Product update) {
		return update.getPrice() != 0;
	}

	private boolean usernameIsNotEmpty(Product update) {
		return update.getName() != null && !update.getName().trim().isEmpty();
	}

}
