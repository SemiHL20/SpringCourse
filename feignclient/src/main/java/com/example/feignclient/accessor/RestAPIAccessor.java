package com.example.feignclient.accessor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.feignclient.model.Product;

@FeignClient("restapi")
public interface RestAPIAccessor {

    @GetMapping("/api/products/{id}")
    public Product getProductFromRestAPI(@PathVariable(value = "id") String id);
}
