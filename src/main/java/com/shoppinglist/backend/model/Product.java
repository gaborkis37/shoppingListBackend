package com.shoppinglist.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int price;
	@ManyToOne
	@JsonBackReference
	private User user;
	private String holderName;

	public Product() {
		super();
	}

	public Product(String name, int price, String holderName) {
		super();
		this.name = name;
		this.price = price;
		this.holderName = holderName;
	}

	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Product(String name, int price, User user) {
		super();
		this.name = name;
		this.price = price;
		this.user = user;
	}
	
	

	public Long getId() {
		return id;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
