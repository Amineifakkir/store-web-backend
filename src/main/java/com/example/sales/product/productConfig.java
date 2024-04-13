package com.example.sales.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class productConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            ProductRepository repository) {
        return args -> {
            Product productA = new Product(
                    "furniture",
                    "test test",
                    "AOH234I",
                    "Something?",
                    289.23
            );
            Product productB = new Product(
                    "furniture",
                    "test test",
                    "BYS92K34I",
                    "Something else",
                    78.28
            );
//            repository.saveAll(
//                    List.of(productA,productB)
//            );
        };
    }
}
