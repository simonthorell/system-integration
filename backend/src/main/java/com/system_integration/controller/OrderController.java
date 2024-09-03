package com.system_integration.controller;

import com.system_integration.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/order")
    public ResponseEntity<String> placeOrder(@RequestBody Map<String, Object> orderData) {
        System.out.println(orderData);
        try {
            int customerId = Integer.parseInt(orderData.get("customer_id").toString());
            List<Map<String, Object>> products = (List<Map<String, Object>>) orderData.get("products");

            orderService.placeOrder(customerId, products);

            return ResponseEntity.ok("Order placed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order: " + e.getMessage());
        }
    }
}
