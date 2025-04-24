package com.example.springmvc.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springmvc.model.Product;
import com.example.springmvc.repository.ProductRepository;


@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping("/products/add")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "edit";
    }

    @PostMapping("/products")
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/";
    }
    
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/products/edit/{id}")
    public String getProduct(Model model, @PathVariable(value = "id") UUID id) {
        model.addAttribute("product", productRepository.findById(id));
        return "edit";
    }
    
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") UUID id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
    
}
