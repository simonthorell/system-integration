package com.system_integration.controller;

import com.system_integration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    // @CrossOrigin(origins = "http://localhost:8080")
    // @GetMapping("/customers/purchase-total")
    // public List<String> getCustomerPurchaseTotals() {
    //     return customerService.getCustomerPurchaseTotals();
    // }
}
