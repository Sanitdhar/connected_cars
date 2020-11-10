package com.connected.cars.locsearch.connector;

import com.connected.cars.locsearch.common.Constants;
import com.connected.cars.locsearch.config.ConnectorProperties;
import com.connected.cars.locsearch.config.Secrets;
import com.connected.cars.locsearch.connector.contract.bycategory.Results;
import com.connected.cars.locsearch.connector.contract.geo.Items;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Objects;

@Component
@Slf4j
public class HereMapConnector {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConnectorProperties connectorProperties;
    @Autowired
    private Secrets secrets;

    @Cacheable(value = "geoLocation")
    public Items getGeoLocation(String name) {
        log.info("inside connector");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put(Constants.Q, Collections.singletonList(name));
        params.put(Constants.API_KEY, Collections.singletonList(secrets.getApiKey()));
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(connectorProperties.getGeoLocation())
                .queryParams(params);
        ResponseEntity<Items> responseEntity
                = restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
                Items.class);
        return responseEntity.getBody();
    }

    @Cacheable(value = "discoverByCategory")
    public Results discoverByCategory(String coordinates, String category) {
        log.info("inside connector");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put(Constants.AT, Collections.singletonList(coordinates));
        params.put(Constants.CAT, Collections.singletonList(category));
        params.put(Constants.API_KEY, Collections.singletonList(secrets.getApiKey()));
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(connectorProperties.getDiscoverByCategory())
                .queryParams(params);
        ResponseEntity<Results> responseEntity
                = restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
                Results.class);
        Objects.requireNonNull(responseEntity.getBody()).setCategory(category);
        return responseEntity.getBody();
    }
}
