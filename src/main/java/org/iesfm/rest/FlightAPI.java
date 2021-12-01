package org.iesfm.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FlightAPI {
    List<Flight> list(String origin);

    Flight getFlight(String flightNumber);
}
