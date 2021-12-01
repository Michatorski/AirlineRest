package org.iesfm.rest;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FlightAPI {
    List<Flight> list(String origin);
}
