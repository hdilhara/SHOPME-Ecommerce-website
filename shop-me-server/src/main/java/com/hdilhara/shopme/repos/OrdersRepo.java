package com.hdilhara.shopme.repos;

import org.springframework.data.repository.CrudRepository;

import com.hdilhara.shopme.entity.Orders;

public interface OrdersRepo extends CrudRepository<Orders,Integer>{

}
