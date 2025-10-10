package com.employee.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private RestTemplate rest;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getOrder")
	public Map<String, Object> getOrderDetails()
	{
		String url = "http://localhost:8081/order/get";
		Map<String, Object> response = rest.getForObject(url, Map.class);
		response.put("message", "Order fetched successfully");
		return response;
	}
	@GetMapping("/info")
	public String getOrderInfo() {
		return "Order Completed";
	}

}
