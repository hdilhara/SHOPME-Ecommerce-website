package com.hdilhara.shopme.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hdilhara.shopme.dto.ProductDetailsDto;
import com.hdilhara.shopme.dto.ProductDto;
import com.hdilhara.shopme.entity.Category;
import com.hdilhara.shopme.services.SystemService;
@CrossOrigin
@RequestMapping("/system")
@RestController
public class SystemController {

	@Autowired
	SystemService service;
	
	@PostMapping("/product/add")
	public ResponseEntity addProduct(@ModelAttribute  ProductDto productDto) {
		
		if(service.addNewProduct(productDto))
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("USER_CREATED_SUCCESSFULLY");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CANT_CREATE_USER");
	}
	@PostMapping("/category/add")
	public ResponseEntity addCategory(@RequestBody Category category) {
		if(service.addNewCategory(category))
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("CATEGORY_CREATED_SUCCESSFULLY");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CANT_CREATE_CATEGORY");
	}
	
	@PostMapping("/product/update/details")
	public ResponseEntity updateProductDetails(@RequestBody ProductDetailsDto details) {
		if(service.upadteProductDetails(details))
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("CATEGORY_UPDATED_SUCCESSFULLY");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CANT_UPDATED_CATEGORY");
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200",methods = RequestMethod.DELETE)
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity deleteProduct(@PathVariable int id) {
		if(service.deleteProduct(id))
			return ResponseEntity.ok("DELETED_SUCCESSFULLY");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CANT_DELETE_PRODUCT");
	}
	
}
