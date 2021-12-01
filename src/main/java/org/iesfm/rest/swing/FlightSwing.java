package org.iesfm.rest.swing;

import org.iesfm.rest.Flight;
import org.iesfm.rest.FlightAPI;
import org.iesfm.rest.clients.FlightClient;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.util.List;

public class FlightSwing {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aerolínea");
        JPanel panel = new JPanel();

        FlightClient flightAPI = new FlightClient(
                "http://localhost:8080",
                new RestTemplate()
        );

        List<Flight> flights = flightAPI.list(null);
        for(Flight flight: flights) {
            panel.add(new JLabel(flight.toString()));
        }

        frame.add(panel);
        frame.setVisible(true);
        frame.setBounds(0, 0, 600, 600);
    }
}
