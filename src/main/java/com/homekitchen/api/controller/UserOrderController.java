package com.homekitchen.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.homekitchen.api.ResponseMessage;
import com.homekitchen.api.entity.*;

@RestController
@RequestMapping(produces="application/json")
public class UserOrderController extends ControllerBase {
	@GetMapping("/order/{id}")
	public @ResponseBody List<UserOrder> getOrderById(Integer id) {
		return orderRepository.findOrderById(id);
	}
	
	@GetMapping("/order/user/{id}")
	public @ResponseBody List<UserOrder> getOrderByUser(Integer id) {
		return orderRepository.findOrderByUser(id);
	}
	
	@GetMapping("/order/{id}/items")
	public @ResponseBody List<OrderItem> getOrderItems(Integer id) {
		return orderItemRepository.findByOrder(id);
	}
	
	@PostMapping(value="/order/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createOrder(@RequestBody UserOrder order) {
		if (userRepository.findUserById(order.getUserId()).isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide valid user for this order"));
		}
				
		if (order.getItems().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "please provide items for this order"));
		}
		
		orderRepository.insertOrder(order);
		
		UserOrder o1=orderRepository.findLastOrderByUser(order.getUserId()).get(0);
		Integer oid = o1.getId();
		
		List<OrderItem> items = order.getItems();
		for (OrderItem item: items) {
			item.setOrder(o1);
			if (item.getUnitPrice()<=0 || item.getCount()<=0) {
				return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide positve unit price and count"));
			}
			if (dishRepository.findById(item.getDishId()).isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide a valid dish for this order item"));
			}
			orderItemRepository.insertOrderItem(item);
		}
		
		return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Success, "Create order successfully"));
	}
}
