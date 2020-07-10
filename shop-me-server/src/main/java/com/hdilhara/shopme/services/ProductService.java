package com.hdilhara.shopme.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.dto.ProductDto;
import com.hdilhara.shopme.entity.Product;
import com.hdilhara.shopme.repos.ProductRepo;

@Service
public class ProductService {

	@Autowired 
	ProductRepo productRepo;
	@Autowired
	CategoryService categoryService;
	
	@Value("${server.nasspath}")
	String imageUrl;

	public Boolean addNewProduct(ProductDto productDto) {
		Product product;
		String fileName="";
		try {
			fileName=productDto.getImage().getOriginalFilename();
			Path path=Paths.get("static/images/"+fileName);
			for(int i=0; i<10; i++) {
				if(Files.exists(path)) {
					fileName=i+fileName;
					path=Paths.get("static/images/"+fileName);
				}
				else
					break;
			}		
			Files.write(path,productDto.getImage().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		try {
			product=new Product(productDto.getTitle(),productDto.getPrice(),imageUrl+"/"+fileName,categoryService.categoryRepo.findById(productDto.getCategoryId()).get());
			productRepo.save(product);
		}
		catch (Exception e) {
			System.out.println("category is not exists!");
			return false;
		}
		return true;
	}

	public List<Product> getAllProducts() {
		List<Product> products =  (List<Product>) productRepo.findAll();	
		return products;
	}

	public Product getProduct(int id) {
		Optional<Product> product=productRepo.findById(id);
		if(product.isPresent())
			return product.get();
		else 
			return null;
	}


	
	
	
}
