package com.example.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.restapi.model.Product;
import com.example.restapi.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products/")
@Tag(name = "ProductsControllerAPI", description = "Products API")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a product by ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Product found")})
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
