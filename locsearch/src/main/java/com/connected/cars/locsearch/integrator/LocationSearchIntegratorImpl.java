package com.connected.cars.locsearch.integrator;

import com.connected.cars.locsearch.common.Constants;
import com.connected.cars.locsearch.connector.HereMapConnector;
import com.connected.cars.locsearch.connector.contract.bycategory.Item;
import com.connected.cars.locsearch.connector.contract.bycategory.Results;
import com.connected.cars.locsearch.connector.contract.geo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class LocationSearchIntegratorImpl implements LocationSearchIntegrator {

    @Autowired
    private HereMapConnector hereMapConnector;

    @Override
    public Results discover(String name, String category) {
        Results results = hereMapConnector.discoverByCategory(getCoordinates(name), category);
        results.getResults().setItems(results.getResults().getItems().stream()
                .sorted(Comparator.comparing(Item::getDistance)).limit(3).collect(Collectors.toList()));
        return results;
    }

    private String getCoordinates(String name) {
        Position position = hereMapConnector.getGeoLocation(name).getItems().get(0).getPosition();
        return String.join(Constants.COMMA_DELIMITER, position.getLat(), position.getLng());
    }
}
