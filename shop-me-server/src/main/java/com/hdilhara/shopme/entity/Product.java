package com.hdilhara.shopme.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String title;
	private float price;
	private String imageUrl;
	
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	public Product() {
		super();
	}
	public Product( String title, float price, String imageUrl) {
		super();
		this.title = title;
		this.price = price;
		this.imageUrl = imageUrl;
	}
	
	public Product(String title, float price, String imageUrl, Category category) {
		super();
		this.title = title;
		this.price = price;
		this.imageUrl = imageUrl;
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
