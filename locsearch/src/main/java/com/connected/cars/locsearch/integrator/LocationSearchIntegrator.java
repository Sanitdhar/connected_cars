package com.connected.cars.locsearch.integrator;

import com.connected.cars.locsearch.connector.contract.bycategory.Results;

public interface LocationSearchIntegrator {

    Results discover(String name, String category);
}
