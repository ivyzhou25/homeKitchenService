package com.homekitchen.api.controller;

import org.springframework.web.bind.annotation.*;

import com.homekitchen.api.ResponseMessage;
import com.homekitchen.api.entity.*;
import com.homekitchen.api.repository.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(produces="application/json")
//@RequestMapping("/api/businesses")
public class DishController extends ControllerBase {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome!!";
	}
	
	@GetMapping("/getDishById")
	public @ResponseBody List<Dish> getDishById(Integer id) {
		return dishRepository.findById(id);
	}
	
	@GetMapping("/getDishByName")
	public @ResponseBody List<Dish> getDishByName(String name) {
		return dishRepository.findByName(name);
	}
	
	@GetMapping("/getDishByBiz")
	public @ResponseBody List<Dish> getDishByBiz(Integer bizId) {
		return dishRepository.findByBiz(bizId);
	}
	
	@GetMapping("/getAllDish")
	public @ResponseBody List<Dish> getAllDish() {
		return dishRepository.findAll();
	}
	
	@PostMapping(value="/addDish", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createDish(Dish dish) {
		System.out.println("get dish "+ dish);
		List<Business> bizList = bizRepository.findById(dish.getBizId());
		if (bizList.isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Business does not exist"));
		}
		if (dish.getName().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide dish name"));
		}
		if (dish.getPrice()<=0) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide a positive number as price"));
		}
		dish.setPriceStart(new Date());
		dishRepository.insertDish(dish);
		return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Success, "Dish "+ dish.getName() + " is added"));
	}
}
