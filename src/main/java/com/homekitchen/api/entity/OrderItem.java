package com.homekitchen.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue()
	private Integer id;
	private Integer dishId;
	@ManyToOne
	private UserOrder order;
	
	private float unitPrice;
	private int count;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDishId() {
		return dishId;
	}
	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}
	
	public UserOrder getOrder() {
		return order;
	}
	public void setOrder(UserOrder order) {
		this.order = order;
	}
	
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
