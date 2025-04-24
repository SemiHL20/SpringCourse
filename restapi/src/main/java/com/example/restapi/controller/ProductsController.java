package com.example.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.restapi.model.Product;
import com.example.restapi.service.ProductService;

@RestController
@RequestMapping("/api/products/")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable(name = "id") String id) {
        return productService.getProduct(id);
    }  

    @PostMapping(consumes = "application/json")
    public Product saveProduct(@RequestBody Product productToSave) {
        return productService.saveProduct(productToSave);
    }

    @PutMapping(path = "{id}", consumes = "application/json")
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id) {
        return productService.updateProduct(productToUpdate, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productService.deleteProduct(id);
    }
}
