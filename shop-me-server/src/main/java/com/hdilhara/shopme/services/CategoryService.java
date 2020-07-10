package com.hdilhara.shopme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.entity.Category;
import com.hdilhara.shopme.entity.Product;
import com.hdilhara.shopme.repos.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	
	public boolean addNewCategory(Category category) {
		categoryRepo.save(category);
		return true;
	}

	public List<Category> getAllCategories() {
		return (List<Category>) categoryRepo.findAll();
		
	}

	public List<Product> getProductByCategory(int id) {	
		return categoryRepo.findById(id).get().getProducts();
	}

	
}
