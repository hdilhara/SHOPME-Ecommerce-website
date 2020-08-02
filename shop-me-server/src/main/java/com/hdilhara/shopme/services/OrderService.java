package com.hdilhara.shopme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.dto.OrderDto;
import com.hdilhara.shopme.dto.ProductQuantityDto;
import com.hdilhara.shopme.entity.OrderProduct;
import com.hdilhara.shopme.entity.Orders;
import com.hdilhara.shopme.entity.UserDetails;
import com.hdilhara.shopme.entity.id.OrderProductId;
import com.hdilhara.shopme.repos.OrderProductRepo;
import com.hdilhara.shopme.repos.OrdersRepo;
import com.hdilhara.shopme.repos.ProductRepo;
import com.hdilhara.shopme.repos.UserDetailsRepo;

@Service
public class OrderService {

	@Autowired
	OrderProductRepo orderProductRepo;
	
	@Autowired
	OrdersRepo ordersRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	UserDetailsRepo userDetailsRepo;
	

	public boolean placeOrder(OrderDto orderDto) {
		int orderId;
		OrderProduct orderProduct;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserDetails> userDetails= userDetailsRepo.findById(authentication.getName());
		if(!userDetails.isPresent())
			return false;
		Orders order=new Orders(orderDto.getOrderPerson(),orderDto.getAddress(),orderDto.getOrderPrice(),userDetails.get());	
		try {
			order = ordersRepo.save(order);
			orderId = order.getOrderId();
			
			for(ProductQuantityDto productQuantity : orderDto.getProductQuantity()) {
				orderProduct = new OrderProduct(productQuantity.getQuantity(),productRepo.findById(productQuantity.getProductId()).get()
						,ordersRepo.findById(orderId).get());
				orderProductRepo.save(orderProduct);
			}
			return true;
		}
		catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
//		orderProductRepo.save(new OrderProduct(55,productRepo.findById(1).get(),ordersRepo.findById(1).get()));
//		orderProductRepo.save(new OrderProduct(55,productRepo.findById(6).get(),ordersRepo.findById(1).get()));
	}
}
