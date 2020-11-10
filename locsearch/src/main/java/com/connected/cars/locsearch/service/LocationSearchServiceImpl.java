package com.connected.cars.locsearch.service;

import com.connected.cars.locsearch.common.Constants;
import com.connected.cars.locsearch.connector.contract.bycategory.Results;
import com.connected.cars.locsearch.integrator.LocationSearchIntegrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LocationSearchServiceImpl implements LocationSearchService {

    @Autowired
    private LocationSearchIntegrator locationSearchIntegrator;

    @Override
    public Map<String, Results> discover(String name) {
        CompletableFuture<Results> restaurant
                = CompletableFuture.supplyAsync(() -> locationSearchIntegrator.discover(name, Constants.RESTAURANT));
        CompletableFuture<Results> petrolStation
                = CompletableFuture.supplyAsync(() -> locationSearchIntegrator.discover(name, Constants.PETROL_STATION));
        CompletableFuture<Results> accommodation
                = CompletableFuture.supplyAsync(() -> locationSearchIntegrator.discover(name, Constants.ACCOMMODATION));
        return Stream.of(restaurant, accommodation, petrolStation)
                .map(CompletableFuture::join)
                .collect(Collectors.toMap(Results::getCategory, Function.identity()));
    }

}
