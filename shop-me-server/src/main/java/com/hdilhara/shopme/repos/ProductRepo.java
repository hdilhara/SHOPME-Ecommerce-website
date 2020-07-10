package com.hdilhara.shopme.repos;

import org.springframework.data.repository.CrudRepository;

import com.hdilhara.shopme.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Integer>{

}
