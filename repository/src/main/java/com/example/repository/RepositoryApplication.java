package com.example.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

import com.example.repository.model.Product;
import com.example.repository.repository.ProductRepository;

@SpringBootApplication
public class RepositoryApplication implements CommandLineRunner {

	private ProductRepository productRepository;
	private RestTemplate restTemplate;

	private final Logger LOG = LoggerFactory.getLogger(RepositoryApplication.class);

	@Autowired
	public void productRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Lazy
	@Autowired
	public void restTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RepositoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Product product1 = new Product();
		product1.setName("Tester Product");
		product1.setType("General");
		product1.setCategory("Test");
		product1.setDescription("Tester product");
		product1.setPrice(4500.00);
		productRepository.save(product1);

		Product product2 = new Product();
		product2.setName("Another tester product");
		product2.setType("Custom");
		product2.setCategory("Test");
		product2.setDescription("Tester product");
		product2.setPrice(0.00);
		productRepository.save(product2);

		Product product3 = new Product();
		product3.setName("Tester Product");
		product3.setType("Specific");
		product3.setCategory("Test");
		product3.setDescription("description");
		product3.setPrice(10.00);
		productRepository.save(product3);

		Product productFromRestTemplate = restTemplate.getForObject("http://localhost:8080/api/products/" + 
			"007958ef-1c47-4d0b-a2e4-2539641f4a4a", Product.class);
		
		LOG.info("Product from RestTemplate: {}", productFromRestTemplate.toString());

//      ---FINDS ALL PRODUCTS---
//		List<Product> products = productRepository.findAll();
//
//		for(Product product : products) {
//            LOG.info("Products found: {}", product.toString());
//		}

//		---FINDS BY TYPE---
//		Product result = productRepository.findByType("Electronics");
//
//		LOG.info("Custom type found: {}", result.toString());

//		---FINDS BY DESC & TYPE---
//		List<Product> results = productRepository.findByDescriptionAndCategory("Dell Inspiron 15 3000", "Computers");
//
//		for(Product product : results) {
//            LOG.info("Products found: {}", product.toString());
//		}

//		---FINDS BY CAT & NAME IN A LIST---
//		List<String> names = new ArrayList<>();
//		names.add("Another tester product");
//		names.add("Tester Product");
//
//		List<Product> results = productRepository.findByCategoryAndNameIn("Test", names);
//
//		for(Product product : results) {
//			LOG.info("Products found: {}", product.toString());
//		}

//		---UPDATES A PRODUCT IF FOUND IN DB---
//		Product productToUpdate = productRepository.findByType("Specific");
//
//		if(productToUpdate != null) {
//            LOG.info("Before update product details: {}", productToUpdate);
//			productToUpdate.setName("Updated product");
//			productToUpdate.setDescription("Updated description");
//
//			Product updated = productRepository.save(productToUpdate);
//            LOG.info("After updating: {}", updated);
//		}

//		---DELETES A PRODUCT IF FOUND IN DB---
		// Product productToDelete = productRepository.findByType("General");

		// if (productToDelete != null) {
		// 	LOG.info("Product count in db: {}", productRepository.count());
		// 	productRepository.delete(productToDelete);
		// 	LOG.info("Product deleted");
		// 	LOG.info("Product count in db: {}", productRepository.count());
		// }


	}


}
