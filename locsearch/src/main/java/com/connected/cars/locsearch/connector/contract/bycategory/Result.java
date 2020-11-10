package com.connected.cars.locsearch.connector.contract.bycategory;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private List<Item> items;
    private String next;
}
