package com.connected.cars.locsearch.service;

import com.connected.cars.locsearch.connector.contract.bycategory.Results;

import java.util.Map;

public interface LocationSearchService {

    Map<String, Results> discover(String name);
}
