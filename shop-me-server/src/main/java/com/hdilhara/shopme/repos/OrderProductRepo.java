package com.hdilhara.shopme.repos;

import org.springframework.data.repository.CrudRepository;

import com.hdilhara.shopme.entity.OrderProduct;
import com.hdilhara.shopme.entity.id.OrderProductId;

public interface OrderProductRepo extends CrudRepository<OrderProduct,OrderProductId >{

}
