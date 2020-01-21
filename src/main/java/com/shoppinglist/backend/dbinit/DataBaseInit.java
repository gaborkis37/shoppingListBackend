package com.shoppinglist.backend.dbinit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppinglist.backend.model.Product;
import com.shoppinglist.backend.model.User;
import com.shoppinglist.backend.repository.ProductRepository;
import com.shoppinglist.backend.repository.UserRepository;

@Service
public class DataBaseInit {

	UserRepository userRepo;
	ProductRepository productRepo;

	@Autowired
	public DataBaseInit(UserRepository userRepo, ProductRepository productRepo) {
		super();
		this.userRepo = userRepo;
		this.productRepo = productRepo;
	}

	@PostConstruct
	public void init() {
		User user1 = new User("initUser", "initUser@gmail.com", "password");
		userRepo.save(user1);

		User user2 = new User("secondUser", "secondUser@gmail.com", "secondPass");
		userRepo.save(user2);

		Product product = new Product("Sajt", 300, user1);
		productRepo.save(product);

		Product product1 = new Product("Tej", 250, user1);
		productRepo.save(product1);

		Product product2 = new Product("Vaj", 400, user1);
		productRepo.save(product2);

		Product product3 = new Product("sör", 300, user2);
		productRepo.save(product3);

		Product product4 = new Product("valami", 250, user2);
		productRepo.save(product4);

		Product product5 = new Product("mégvalami", 400, user2);
		productRepo.save(product5);
	}

}
