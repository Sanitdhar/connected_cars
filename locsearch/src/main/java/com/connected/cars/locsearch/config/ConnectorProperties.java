package com.connected.cars.locsearch.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "here-maps")
public class ConnectorProperties {

    private String geoLocation;
    private String discoverByCategory;
}
