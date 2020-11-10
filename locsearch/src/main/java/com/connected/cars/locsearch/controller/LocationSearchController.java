package com.connected.cars.locsearch.controller;

import com.connected.cars.locsearch.connector.contract.bycategory.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface LocationSearchController {

    @GetMapping("/discover")
    Map<String, Results> discover(@RequestParam("name") String name);
}
