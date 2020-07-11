package com.hdilhara.shopme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.dto.ProductDetailsDto;
import com.hdilhara.shopme.dto.ProductDto;
import com.hdilhara.shopme.entity.Category;

@Service
public class SystemService {

	@Autowired 
	ProductService productService;
	@Autowired 
	CategoryService categoryService;

	public boolean addNewProduct(ProductDto productDto) {
		return productService.addNewProduct(productDto);
	}

	public boolean addNewCategory(Category category) {
		return categoryService.addNewCategory(category);
	}

	public boolean upadteProductDetails(ProductDetailsDto details) {
		return productService.upadteProductDetails(details);
	}

	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		return productService.deleteProduct(id);
	}
	
	
}
