package com.ord.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ord.messaging.OrderProducer;
import com.ord.model.InventoryItem;
import com.ord.model.OrderEvent;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private RestTemplate restTemplate;

    private final String INVENTORY_URL = "http://localhost:8082/api/inventory/items/all";

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent orderEvent) {
        orderProducer.sendOrderEvent(orderEvent);
        return ResponseEntity.ok("Order event sent.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
        InventoryItem[] items = restTemplate.getForObject(INVENTORY_URL, InventoryItem[].class);
        return ResponseEntity.ok(Arrays.asList(items));
    }
}
