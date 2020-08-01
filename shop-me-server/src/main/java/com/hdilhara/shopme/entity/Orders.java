package com.hdilhara.shopme.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private String orderPerson;
	private String address;
	private Float orderPrice;
	private Date date;
	
	
	
	public Orders(String orderPerson, String address, Float orderPrice) {
		super();
		this.orderPerson = orderPerson;
		this.address = address;
		this.orderPrice = orderPrice;
	}
	public Orders() {
		super();
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
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
	public Float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Float orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
