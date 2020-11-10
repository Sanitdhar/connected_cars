package com.connected.cars.locsearch.connector.contract.bycategory;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
public class Item {
    private ArrayNode position;
    private int distance;
    private String title;
    private double averageRating;
    private ObjectNode category;
    private String icon;
    private String vicinity;
    private String href;
    private String id;
}
