package com.example.feignclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.example.feignclient.accessor.RestAPIAccessor;
import com.example.feignclient.model.Product;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignclientApplication implements CommandLineRunner{

	private RestAPIAccessor restAPIAccessor;

	private Logger LOG = LoggerFactory.getLogger(FeignclientApplication.class);

	@Autowired
	public void setRestAPIAccessor(RestAPIAccessor restAPIAccessor) {
		this.restAPIAccessor = restAPIAccessor;
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignclientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product productFromFeignClient = restAPIAccessor.getProductFromRestAPI("67e25ca9-e58d-4052-8ab1-6b42020da8b2");
		LOG.info("Product from RESTAPI: " + productFromFeignClient.toString());
	}

}
