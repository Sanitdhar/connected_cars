package com.connected.cars.locsearch.connector;

import com.connected.cars.locsearch.config.ConnectorProperties;
import com.connected.cars.locsearch.config.Secrets;
import com.connected.cars.locsearch.connector.contract.bycategory.Results;
import com.connected.cars.locsearch.connector.contract.geo.Items;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class HereMapConnectorImplTests {

    @InjectMocks
    private HereMapConnector hereMapConnector;

    @Mock
    private RestTemplate restTemplate;
    @MockBean
    private Secrets secrets;
    @MockBean
    private ConnectorProperties connectorProperties;
    @Spy
    private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder() {
        public RestTemplate build() {
            return restTemplate;
        }
    };

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
        given(restTemplateBuilder.build()).willReturn(restTemplate);
        given(secrets.getApiKey()).willReturn("xyz");
        given(connectorProperties.getDiscoverByCategory()).willReturn("yui");
        given(connectorProperties.getGeoLocation()).willReturn("sadcf");
    }

    @Test
    void testGeoLocation() {
        given(restTemplate.getForEntity(anyString(), any())).willReturn(ResponseEntity.ok()
                .body(items));
        Items items= hereMapConnector.getGeoLocation("abc");
        Assertions.assertTrue(Objects.nonNull(items));
    }

    @Test
    void testDiscoverByCategory() {
        given(restTemplate.getForEntity(anyString(), any())).willReturn(ResponseEntity.ok()
                .body(results));
        Results results= hereMapConnector.discoverByCategory("abc","sd");
        Assertions.assertTrue(Objects.nonNull(results));
    }

}
