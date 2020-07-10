package com.hdilhara.shopme.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hdilhara.shopme.dto.ProductDto;
import com.hdilhara.shopme.entity.Category;
import com.hdilhara.shopme.entity.Product;
import com.hdilhara.shopme.services.CategoryService;
import com.hdilhara.shopme.services.ProductService;

@CrossOrigin
@RequestMapping("/product")
@RestController
public class ProductController {

	@Autowired
	ProductService service;
	@Autowired
	CategoryService serviceCategory;
	
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id) {
		return service.getProduct(id);
	}
	
	@GetMapping("/category/{id}")
	public List<Product> getAllproductsByCategory(@PathVariable int id) {
		return serviceCategory.getProductByCategory(id);
	}
	
	@GetMapping("/categories")
	public List<Category> getAllCategories(){
		return serviceCategory.getAllCategories();
	}
}
