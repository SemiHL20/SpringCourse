package com.example.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.repository.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByType(String type);
    List<Product> findByDescriptionAndCategory(String description, String category);
    List<Product> findByCategoryAndNameIn(String category, List<String> name);
}
