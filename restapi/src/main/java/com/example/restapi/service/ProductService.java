package com.example.restapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(String id) {
        LOG.info("Getting the product with id {}", id);
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        LOG.info("Saving the product with id {}", product.getId());
        try {
            productRepository.save(product);
            return product;
        } catch (Exception e) {
            LOG.error("Error saving the product: " + e.getMessage());
            return null;
        } 
    }

    public Product updateProduct(Product productToUpdate, String id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        LOG.info("Updating the product with id {}", id);
        try {
            existingProduct.setName(productToUpdate.getName());
            existingProduct.setDescription(productToUpdate.getDescription());
            existingProduct.setType(productToUpdate.getType());
            existingProduct.setCategory(productToUpdate.getCategory());
            return productRepository.save(existingProduct);
        } catch (Exception e) {
            LOG.error("Error updating the product: " + e.getMessage());
        }
        return null;
    }

    public void deleteProduct(String id) {
        LOG.info("Deleting the product with id {}", id);
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("Error deleting the product: " + e.getMessage());
        }
            
    }
}
