package com.orderTest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderTest.model.Order;

@RestController
@RequestMapping("/order")
public class OrderController {
	@PostMapping("/create")
	public Order createOrder(@RequestBody Order order) {
		return new Order(order.getId(),order.getName(),order.getPrice());
	}
	@GetMapping("/get")
	public Order getOrderById() {
		return new Order(2,"Computer",30000);
	}
	
	

}
