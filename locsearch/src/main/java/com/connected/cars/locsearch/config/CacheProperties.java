package com.connected.cars.locsearch.config;

import lombok.Data;

@Data
public class CacheProperties {

    private String name;
    private String maximumSize;
    private String expireAfterWrite;
}
