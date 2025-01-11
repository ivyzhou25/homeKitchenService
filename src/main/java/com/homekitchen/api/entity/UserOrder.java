package com.homekitchen.api.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class UserOrder {
	@Id
	@GeneratedValue()
	private Integer id;
	private Integer userId;
	private float deliveryCost;
	private Date orderDate;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, mappedBy="order")
	private List<OrderItem> items;
	
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public float getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(float deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}
