package com.hdilhara.shopme.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.dto.ProductDetailsDto;
import com.hdilhara.shopme.dto.ProductDto;
import com.hdilhara.shopme.entity.Category;
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

	public boolean upadteProductDetails(ProductDetailsDto details) {
		Optional<Product> optProduct = productRepo.findById(details.getProductId());
		if(!optProduct.isPresent())
			return false;
		else {
			Product product=optProduct.get();
			Optional<Category> category= categoryService.categoryRepo.findById(details.getCategoryId());
			product.setPrice(details.getPrice());
			product.setCategory(category.get());
			product.setTitle(details.getTitle());
			productRepo.save(product);
		}
		return true;
	}

	public boolean deleteProduct(int id) {
		try {
			String imgURL=productRepo.findById(id).get().getImageUrl();
			String[] urlSplit=imgURL.split("/");
			imgURL=urlSplit[urlSplit.length-1];
			Path path=Paths.get("static/images/"+imgURL);
			Files.deleteIfExists(path);
			productRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	
	
	
}
