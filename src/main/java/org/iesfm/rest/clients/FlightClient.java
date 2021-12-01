package org.iesfm.rest.clients;

import org.iesfm.rest.Flight;
import org.iesfm.rest.FlightAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightClient implements FlightAPI {
    private RestTemplate restTemplate;

    public FlightClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Flight> list(String origin) {

        Map<String, Object> params = new HashMap<>();
        if (origin != null) {
            params.put("origin", origin);
        }

        Flight[] flights = restTemplate.getForObject(
                "/flights",
                Flight[].class,
                params
        );
        return Arrays.asList(flights);

    }

    @Override
    public Flight getFlight(String flightNumber) {
        try {
            return restTemplate
                    .getForObject(
                            "/flights/" + flightNumber,
                            Flight.class
                    );
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    @Override
    public void createFlight(Flight flight) {

        restTemplate.postForObject("/flights", flight, Void.class);

    }
}
