package com.tqk.demo;

import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(path = "/product")
public class ProductResource {

    private final Environment environment;
    private final EasyRandom generator = new EasyRandom();
    @Autowired
    public ProductResource(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductPage() {
        for (String profile: environment.getActiveProfiles()) {
            log.info("Active profiles: " + profile);
        }
        return createProducts();
    }

    public List<Product> createProducts() {
        SecureRandom random = new SecureRandom();
        return generator.objects(Product.class, random.nextInt(30)).collect(Collectors.toList());
    }
}
