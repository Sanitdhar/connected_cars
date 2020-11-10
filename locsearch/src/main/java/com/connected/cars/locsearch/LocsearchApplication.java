package com.connected.cars.locsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LocsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocsearchApplication.class, args);
    }

}
