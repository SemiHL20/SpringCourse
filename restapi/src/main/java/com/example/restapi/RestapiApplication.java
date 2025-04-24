package com.example.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.restapi.repository.ProductRepository;
import com.example.restapi.model.Product;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RestapiApplication implements CommandLineRunner{

	private ProductRepository productRepository;

	@Autowired
	public void productRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Product testProduct = new Product();
		testProduct.setName("Test Product");
		testProduct.setDescription("This is a test product");
		testProduct.setType("CUSTOM");
		testProduct.setCategory("SPECIAL");
		productRepository.save(testProduct);
	}

}
