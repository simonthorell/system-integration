package com.system_integration.controller;

import com.system_integration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers/who-bought")
    public List<String> getCustomersWhoBought(@RequestParam String productName,
                                              @RequestParam String color,
                                              @RequestParam String size,
                                              @RequestParam String brand) {
        return customerService.findCustomersWhoBought(productName, color, size, brand);
    }
}
