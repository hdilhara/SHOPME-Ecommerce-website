package com.hdilhara.shopme.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.hdilhara.shopme.entity.id.OrderProductId;

@Entity
public class OrderProduct {

	@EmbeddedId
	private OrderProductId id=new OrderProductId();
	private int quantity;
	
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "productId")
	private Product product;
	
	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "orderId")
	private Orders order;
	

	public OrderProduct() {
		super();
	}

	
	
	public OrderProduct(int quantity, Product product, Orders order) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.order = order;
	}



	public OrderProductId getId() {
		return id;
	}

	public void setId(OrderProductId id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
