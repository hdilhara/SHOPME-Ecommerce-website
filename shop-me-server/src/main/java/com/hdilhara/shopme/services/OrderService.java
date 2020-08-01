package com.hdilhara.shopme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.dto.OrderDto;
import com.hdilhara.shopme.dto.ProductQuantityDto;
import com.hdilhara.shopme.entity.OrderProduct;
import com.hdilhara.shopme.entity.Orders;
import com.hdilhara.shopme.entity.id.OrderProductId;
import com.hdilhara.shopme.repos.OrderProductRepo;
import com.hdilhara.shopme.repos.OrdersRepo;
import com.hdilhara.shopme.repos.ProductRepo;

@Service
public class OrderService {

	@Autowired
	OrderProductRepo orderProductRepo;
	
	@Autowired
	OrdersRepo ordersRepo;
	
	@Autowired
	ProductRepo productRepo;

	public void placeOrder(OrderDto orderDto) {
		int orderId;
		OrderProduct orderProduct;

		Orders order=new Orders(orderDto.getOrderPerson(),orderDto.getAddress(),orderDto.getOrderPrice());	
		try {
			order = ordersRepo.save(order);
			orderId = order.getOrderId();
			
			for(ProductQuantityDto productQuantity : orderDto.getProductQuantity()) {
				orderProduct = new OrderProduct(productQuantity.getQuantity(),productRepo.findById(productQuantity.getProductId()).get()
						,ordersRepo.findById(orderId).get());
				orderProductRepo.save(orderProduct);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
//		orderProductRepo.save(new OrderProduct(55,productRepo.findById(1).get(),ordersRepo.findById(1).get()));
//		orderProductRepo.save(new OrderProduct(55,productRepo.findById(6).get(),ordersRepo.findById(1).get()));
	}
}
