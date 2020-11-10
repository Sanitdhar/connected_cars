package com.connected.cars.locsearch.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@Getter
@Setter
@ConfigurationProperties(value = "cache")
public class CacheConfig {

    private CacheProperties geoLocation;
    private CacheProperties discoverByCategory;

    @Bean
    public CaffeineCache geoLocationCache() {
        return new CaffeineCache(geoLocation.getName(), Caffeine.newBuilder()
                .maximumSize(Long.parseLong(geoLocation.getMaximumSize()))
                .build());
    }

    @Bean
    public CaffeineCache discoverByCategoryCache() {
        return new CaffeineCache(discoverByCategory.getName(), Caffeine.newBuilder()
                .maximumSize(Long.parseLong(discoverByCategory.getMaximumSize()))
                .expireAfterWrite(Long.parseLong(discoverByCategory.getExpireAfterWrite()), TimeUnit.SECONDS)
                .build());
    }
}
