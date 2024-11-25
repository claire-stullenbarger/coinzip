package com.example.coinzipp;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
@EnableCaching
public class CoinzippApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinzippApplication.class, args);
    }
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://localhost:3306/users");
        dataSource.setUsername("root");
        dataSource.setPassword("Daisy2004");
        return dataSource;
    }

    // You can add more @Bean methods here for other components

    // Example: Initialize database or perform startup tasks
    @PostConstruct
    public void init() {
        // Perform any initialization logic here
        System.out.println("Coinzipp application initialized!");
    }

}
