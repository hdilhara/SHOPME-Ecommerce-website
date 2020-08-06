package com.hdilhara.shopme.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		Map<String, String> map=new HashMap<String, String>();
		if(service.addNewProduct(productDto))
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(map.put("msg","PRODUCT_CREATED_SUCCESSFULLY"));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map.put("msg","CANT_CREATE_PRODUCT"));
	}
	@PostMapping("/category/add")
	public ResponseEntity addCategory(@RequestBody Category category) {
		Map<String, String> map = new HashMap<String, String>();
		if(service.addNewCategory(category))
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(map.put("msg","CATEGORY_CREATED_SUCCESSFULLY"));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map.put("msg","CANT_CREATE_CATEGORY"));
	}
	
	
	@PostMapping("/product/update/details")
	public ResponseEntity/*Map<String, String>*/ updateProductDetails(@RequestBody ProductDetailsDto details) {
		Map<String, String> map = new HashMap<String, String>();
		if(service.upadteProductDetails(details)) {
			map.put("msg", "PRODUCT_UPDATED_SUCCESSFULLY");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
			
		}
		else {
			map.put("msg", "PRODUCT_UPDATED_SUCCESSFULLY");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}		
	}
	
	@CrossOrigin(origins = "http://localhost:4200",methods = RequestMethod.DELETE)
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity deleteProduct(@PathVariable int id) {
		Map<String, String> map = new HashMap<String, String>();
		if(service.deleteProduct(id))
			return ResponseEntity.ok(map.put("msg", "DELETED_SUCCESSFULLY"));
		else
			return ResponseEntity.ok(map.put("msg", "CANT_DELETE_PRODUCT"));
	}
	
	@GetMapping("/orders")
	public ResponseEntity<?> name() {
		Map<String,String> response = new HashMap<String, String>();
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAllOrders());
	}
}
