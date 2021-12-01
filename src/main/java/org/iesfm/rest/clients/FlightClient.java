package org.iesfm.rest.clients;

import org.iesfm.rest.Flight;
import org.iesfm.rest.FlightAPI;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightClient implements FlightAPI {
    private String flightService;
    private RestTemplate restTemplate;

    public FlightClient(String flightService, RestTemplate restTemplate) {
        this.flightService = flightService;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Flight> list(String origin) {
        if(origin == null) {
            Flight[]  flights = restTemplate.getForObject(
                    flightService + "/flights",
                    Flight[].class
            );
            return Arrays.asList(flights);
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("origin", origin);
            Flight[]  flights = restTemplate.getForObject(
                    "http://localhost:8080/flights",
                    Flight[].class,
                    params
            );
            return Arrays.asList(flights);
        }
    }
}
