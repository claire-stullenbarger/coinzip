package com.example.coinzipp;

//import jakarta.annotation.PostConstruct;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableCaching
public class CoinzippApplication {


    public static void main(String[] args) {
        SpringApplication.run(CoinzippApplication.class, args);
    }

    @PostConstruct
    public void init() {
        // Perform any initialization logic here
        System.out.println("Coinzipp application initialized!");
    }
}