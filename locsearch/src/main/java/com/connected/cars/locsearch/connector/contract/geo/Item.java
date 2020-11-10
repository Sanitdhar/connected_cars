package com.connected.cars.locsearch.connector.contract.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String title;
    private ObjectNode address;
    private Position position;
    private ArrayNode categories;
    private ObjectNode scoring;
}
