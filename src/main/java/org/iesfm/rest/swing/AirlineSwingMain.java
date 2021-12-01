package org.iesfm.rest.swing;

import org.iesfm.rest.Flight;
import org.iesfm.rest.clients.ErrorHandler;
import org.iesfm.rest.clients.FlightClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.*;
import java.util.List;

public class AirlineSwingMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aerolínea");
        JPanel panel = new JPanel();

        FlightClient flightAPI = new FlightClient(
                new RestTemplateBuilder()
                        .errorHandler(new ErrorHandler())
                        .rootUri("http://localhost:8080")
                        .build()
        );
        ResponseEntity<Void> response = flightAPI.createFlight(new Flight(
                "23234",
                "Barcelona",
                "Madrid"
        ));
        if(response.getStatusCodeValue() == HttpStatus.CREATED.value()) {
            JOptionPane.showMessageDialog(frame, "Se ha creado el vuelo");
        } else if(response.getStatusCodeValue() == HttpStatus.CONFLICT.value()){
            JOptionPane.showMessageDialog(frame, "Ya existía el vuelo");
        }

        List<Flight> flights = flightAPI.list(null);
        for (Flight flight : flights) {
            panel.add(new JLabel(flight.toString()));
        }
        try {
            Flight flight = flightAPI.getFlight("no existe");
        }catch (HttpClientErrorException.NotFound e) {
            JOptionPane.showMessageDialog(frame, "No se encuentra el vuelo");
        }

        frame.add(panel);
        frame.setVisible(true);
        frame.setBounds(0, 0, 600, 600);
    }
}
