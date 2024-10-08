package com.system_integration.controller;

import com.system_integration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // API endpoint to get customers who bought a specific product
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/customers/who-bought")
    public List<String> getCustomersWhoBought(@RequestParam String productName,
                                              @RequestParam String color,
                                              @RequestParam String size,
                                              @RequestParam String brand) {
        return customerService.findCustomersWhoBought(productName, color, size, brand);
    }

    // API endpoint to get the total purchase amount of each customer
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/customers/purchase-total")
    public List<Map<String, Object>> getCustomerPurchaseTotals() {
        return customerService.getCustomerPurchaseTotals();
    }

    // API endpoint to get all customers
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/customers")
    public List<Map<String, Object>> getCustomers() {
        return customerService.getCustomers();
    }
}
