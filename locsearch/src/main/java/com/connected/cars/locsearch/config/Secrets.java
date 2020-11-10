package com.connected.cars.locsearch.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "secrets")
public class Secrets {

    private String apiKey;
    private String appId;
}
