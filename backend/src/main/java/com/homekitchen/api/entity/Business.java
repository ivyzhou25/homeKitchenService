package com.homekitchen.api.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Business {
	@Id
	@GeneratedValue()
	private Integer id;
	private String bizName;
	private String bizAddr;
	private String bizCity;
	private String bizState;
	private String bizZipcode;
	private String description;
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBizName() {
		return bizName;
	}
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	public String getBizAddr() {
		return bizAddr;
	}
	public void setBizAddr(String bizAddr) {
		this.bizAddr = bizAddr;
	}
	public String getBizCity() {
		return bizCity;
	}
	public void setBizCity(String bizCity) {
		this.bizCity = bizCity;
	}
	public String getBizState() {
		return bizState;
	}
	public void setBizState(String bizState) {
		this.bizState = bizState;
	}
	public String getBizZipcode() {
		return bizZipcode;
	}
	public void setBizZipcode(String bizZipcode) {
		this.bizZipcode = bizZipcode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
