package com.homekitchen.api;



public class ResponseMessage {
	private STATUS status;
	private String message;
	public enum STATUS {
		  Error,
		  Success
		}
	private int id;
	
	public STATUS getStatus() {
		return status;
	}


	public void setStatus(STATUS status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public ResponseMessage(STATUS status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public ResponseMessage(STATUS status, String message, int id) {
		this.status = status;
		this.message = message;
		this.id=id;
	}
}
