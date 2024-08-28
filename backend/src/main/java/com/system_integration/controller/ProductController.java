package com.system_integration.controller;

import com.system_integration.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/product/per-category")
    public List<Map<String, Object>> getProductsPerCategory() {
        System.out.println("running..");
        return productService.getProductsPerCategory();
    }
}
