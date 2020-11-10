package com.connected.cars.locsearch.integrator;

import com.connected.cars.locsearch.common.Constants;
import com.connected.cars.locsearch.connector.HereMapConnector;
import com.connected.cars.locsearch.connector.contract.bycategory.Results;
import com.connected.cars.locsearch.connector.contract.geo.Items;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class LocationSearchIntegratorImplTests {

    @InjectMocks
    private LocationSearchIntegratorImpl locationSearchIntegrator;

    @MockBean
    private HereMapConnector hereMapConnector;

    private Results results;
    private Items items;

    @BeforeEach
    public void setUp() throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File resultFile = ResourceUtils.getFile("classpath:data/Results.json");
        File itemsFile = ResourceUtils.getFile("classpath:data/Items.json");
        results=objectMapper.readValue(resultFile, Results.class);
        items = objectMapper.readValue(itemsFile, Items.class);
        given(hereMapConnector.discoverByCategory(anyString(),anyString())).willReturn(results);
        given(hereMapConnector.getGeoLocation(anyString())).willReturn(items);
    }

    @Test
    public void test1(){
        Results results=locationSearchIntegrator.discover("Brandenburge%20Tor,Berlin", Constants.RESTAURANT);

        Assertions.assertEquals(results.getResults().getItems().size(),3);
        Assertions.assertTrue(results.getResults().getItems().get(0).getDistance()<
                results.getResults().getItems().get(1).getDistance() && results.getResults().getItems().get(1).getDistance()<
                results.getResults().getItems().get(2).getDistance());

    }
}
