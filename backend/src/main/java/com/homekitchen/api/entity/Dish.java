package com.homekitchen.api.entity;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Dish {
	@Id
	@GeneratedValue()
	private Integer id;
	private String name;
	private String cuisine;
	private String description;
	private float price;
	private Date priceStart;
	private Date priceEnd;
	private Integer bizId;
	
	
	public Integer getId() {
	   return id;
	}
	
	public void setId(Integer id) {
	  this.id = id;
	}
	  
	public String getName() {
	  return name;
	}

	public void setName(String name) {
	  this.name = name;
	}
	
	public String getCuisine() {
	  return cuisine;
	}

	public void setCuisine(String cuisine) {
	  this.cuisine = cuisine;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getPrice() {
	  return price;
	}

	public void setPrice(float price) {
	  this.price = price;
	}
	
	public Date getPriceStart() {
	  return priceStart;
	}

	public void setPriceStart(Date date) {
	  this.priceStart = date;
	}
	
	public Date getPriceEnd() {
	  return priceEnd;
	}

	public void setPriceEnd(Date date) {
	  this.priceEnd = date;
	}
	
	public Integer getBizId() {
	   return bizId;
	}
	
	public void setBizId(Integer bid) {
	  this.bizId = bid;
	}
}
