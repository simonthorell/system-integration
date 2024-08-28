package com.system_integration.controller;

import com.system_integration.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SalesController {

    @Autowired
    private SalesService salesService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/sales/best-month")
    public Map<String, Object> getBestSalesMonthThisYear() {
        return salesService.getBestSalesMonthThisYear();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/sales/top-5-products")
    public List<Map<String, Object>> getTopFiveProducts() {
        return salesService.getTopFiveProducts();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/sales/get-order-for-city")
    public List<Map<String, Object>> getOrderForCity(@RequestParam double threshold) {
        return salesService.getOrderForCity(threshold);
    }
}
