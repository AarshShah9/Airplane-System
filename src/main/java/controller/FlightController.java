package main.java.controller;

import main.java.view.FlightView;
import main.java.viewModel.FlightViewModel;

import java.awt.event.*;

public class FlightController implements ActionListener {
    private Database db;
    private MainController mainController;
    private FlightViewModel flightViewModel[];

    private FlightView flightView;

    public FlightController(Database db, MainController mc) {
        this.db = db;
        this.mainController = mc;

        flightView = new FlightView(getFlightViewModels());
        // flightView.setVisible(true);

    }

    public FlightView getView() {
        return flightView;
    }

    public void addListeners() {
    }

    public FlightViewModel[] getFlightViewModels() {
        // Write SQL query to get all flights
        // String query = "SELECT * FROM flights";
        // blah blah blah
        // Fake it for now
        FlightViewModel fvm[] = new FlightViewModel[3];
        fvm[0] = new FlightViewModel("Destination 1", "Departure 1", "Departure Time 1", "Arrival Time 1", "Price 1",
                "Flight Number 1");

        fvm[1] = new FlightViewModel("Destination 2", "Departure 2", "Departure Time 2", "Arrival Time 2", "Price 2",
                "Flight Number 2");

        fvm[2] = new FlightViewModel("Destination 3", "Departure 3", "Departure Time 3", "Arrival Time 3", "Price 3",
                "Flight Number 3");

        return fvm;
    }
    // include method for returning flights based on search criteria

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedRow = e.getActionCommand();
        System.out.println("Selected row: " + selectedRow);
    }
    // Include method for admin creating flight

    // include method for admin updating flight

    // include method for admin deleting flight

}
