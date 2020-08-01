package com.hdilhara.shopme.dto;

import java.util.List;

public class OrderDto {

	private String orderPerson;
	private String address;
	private float orderPrice;
	private List<ProductQuantityDto> productQuantity;
	public String getOrderPerson() {
		return orderPerson;
	}
	public void setOrderPerson(String orderPerson) {
		this.orderPerson = orderPerson;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	public List<ProductQuantityDto> getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(List<ProductQuantityDto> productQuantity) {
		this.productQuantity = productQuantity;
	}
	@Override
	public String toString() {
		return "OrderDto [orderPerson=" + orderPerson + ", address=" + address + ", orderPrice=" + orderPrice
				+ ", productQuantity=" + productQuantity + "]";
	}
	
	
}
