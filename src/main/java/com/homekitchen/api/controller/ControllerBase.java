package com.homekitchen.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.homekitchen.api.repository.*;

public class ControllerBase {
	@Autowired
	protected DishRepository dishRepository;
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected BusinessRepository bizRepository;
	
	@Autowired
	protected OrderItemRepository orderItemRepository;
	
	@Autowired
	protected OrderRepository orderRepository;
}
