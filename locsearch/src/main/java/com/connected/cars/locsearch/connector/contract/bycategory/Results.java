package com.connected.cars.locsearch.connector.contract.bycategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Results {
    private Result results;
    @JsonIgnore
    private String category;
}
