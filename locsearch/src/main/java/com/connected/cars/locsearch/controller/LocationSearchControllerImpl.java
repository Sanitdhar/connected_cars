package com.connected.cars.locsearch.controller;

import com.connected.cars.locsearch.connector.contract.bycategory.Results;
import com.connected.cars.locsearch.service.LocationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LocationSearchControllerImpl implements LocationSearchController {
    @Autowired
    private LocationSearchService locationSearchService;

    @Override
    public Map<String, Results> discover(String name) {
        return locationSearchService.discover(name);
    }
}
