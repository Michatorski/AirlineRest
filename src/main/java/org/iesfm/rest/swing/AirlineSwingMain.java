package org.iesfm.rest.swing;

import org.iesfm.rest.Flight;
import org.iesfm.rest.clients.FlightClient;
import org.springframework.boot.web.client.RestTemplateBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirlineSwingMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aerolínea");
        JPanel panel = new JPanel();

        FlightClient flightAPI = new FlightClient(
                new RestTemplateBuilder()
                        .rootUri("http://localhost:8080")
                        .build()
        );

        JTextField flightNumberField = new JTextField();
        flightNumberField.setText("0000000");
        panel.add(flightNumberField);
        JButton boton = new JButton("Pedir vuelo");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Flight flight = flightAPI.getFlight(flightNumberField.getText());
                if(flight == null) {
                    JOptionPane.showMessageDialog(frame, "No existe el vuelo");
                } else {
                    JOptionPane.showMessageDialog(frame, flight.toString());
                }
            }
        });
        panel.add(boton);
        frame.add(panel);
        frame.setVisible(true);
        frame.setBounds(0, 0, 600, 600);
    }
}
