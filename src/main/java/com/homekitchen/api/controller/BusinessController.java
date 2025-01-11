package com.homekitchen.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.homekitchen.api.ResponseMessage;
import com.homekitchen.api.entity.Business;

@RestController
@RequestMapping(produces="application/json")
public class BusinessController extends ControllerBase {
	@GetMapping("/getBizByName")
	public @ResponseBody List<Business> getBizByName(String name) {
		return bizRepository.findByName(name);
	}
	
	@GetMapping("/getBizById")
	public @ResponseBody List<Business> getBizById(Integer id) {
		return bizRepository.findById(id);
	}
	
	@GetMapping("/getBizByUser")
	public @ResponseBody List<Business> getBizByUser(Integer id) {
		return bizRepository.findByUser(id);
	}
	
	@PostMapping(value="/addBusiness", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createUser(Business biz) {
		if (biz.getBizName().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide business name"));
		}
		if (biz.getBizCity().isEmpty() || biz.getBizState().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide business city and state"));
		}
		if (biz.getBizZipcode().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide business zipcode"));
		}
		
		if (userRepository.findUserById(biz.getUserId()).isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide a valid user Id"));
		}
		
		bizRepository.insertBusiness(biz);
		return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Success, "Create business " + biz.getBizName() + " successfully"));
	}
}
