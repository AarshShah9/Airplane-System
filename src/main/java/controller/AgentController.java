package controller;

import view.FlightView;
import view.PassengerView;
import ViewModel.FlightViewModel;
import ViewModel.PassengerViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * The AgentController class manages the interaction between the database,
 * FlightView, PassengerView, and other controllers for agents.
 */
public class AgentController implements ActionListener {
    private Database db;
    private MainController mc;
    private PassengerView passengerView;
    private FlightView flightView;

    private FlightViewModel[] flightViewModels;
    private PassengerViewModel[] passengerViewModels;

    /**
     * Constructs an AgentController.
     *
     * @param db The database instance to access flight and passenger data.
     * @param mc The MainController for managing the application's main views.
     */
    public AgentController(Database db, MainController mc) {
        this.db = db;
        this.mc = mc;
        this.flightView = new FlightView(getFlightViewModels());
        this.flightView.addFlightSelectionListener(this);
    }

    /**
     * Retrieves flight data from the database based on the agent's username.
     *
     * @return An array of FlightViewModel objects representing flight data.
     */
    public FlightViewModel[] getFlightViewModels() {
        String query = "SELECT * FROM flights WHERE flight_id IN (SELECT flight_id FROM crew WHERE username = '"
                + mc.getUser().getUsername() + "');";
        try (ResultSet rs = db.executeQuery(query)) {
            List<FlightViewModel> flightList = new ArrayList<>();
            while (rs.next()) {
                int flightId = rs.getInt("flight_id");
                String arrivalTime = rs.getString("arrival_time");
                String departureTime = rs.getString("departure_time");
                String destination = rs.getString("arrival_airport_id");
                String leavingFrom = rs.getString("departure_airport_id");
                double price = rs.getDouble("price");
                flightList.add(new FlightViewModel(flightId, destination, leavingFrom, departureTime,
                        arrivalTime, price, Integer.toString(flightId)));
            }
            flightViewModels = flightList.toArray(new FlightViewModel[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightViewModels;
    }

    /**
     * Retrieves and displays passenger information for the selected flight.
     *
     * @param e The ActionEvent triggered by selecting a flight in the view.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedRow = e.getActionCommand();
        FlightViewModel flight = flightViewModels[Integer.parseInt(selectedRow)];
        int flight_id = flight.FlightId; // Grab selected flight's id

        // Grab usernames and seat ids of all passengers on the selected flight
        String selectUsernamesQuery = "SELECT username, seat_id FROM bookings WHERE flight_id = '" + flight_id + "';";
        List<PassengerViewModel> passengerList = new ArrayList<>();

        try (ResultSet rs = db.executeQuery(selectUsernamesQuery)) {
            while (rs.next()) {
                String username = rs.getString("username");
                int seatId = rs.getInt("seat_id");
                String seatNumber = getSeatNumber(seatId);
                passengerList.add(new PassengerViewModel(username, seatId, seatNumber));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // Grab first names and last names of all passengers on the selected flight
        for (PassengerViewModel entry : passengerList) {
            String selectNamesQuery = "SELECT first_name, last_name FROM users WHERE username = '" + entry.getUserName()
                    + "';";
            try (ResultSet rs_names = db.executeQuery(selectNamesQuery)) {
                while (rs_names.next()) {
                    String firstName = rs_names.getString("first_name");
                    String lastName = rs_names.getString("last_name");
                    entry.setFirstName(firstName);
                    entry.setLastName(lastName);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Use the list of passengers to create a table
        passengerViewModels = passengerList.toArray(new PassengerViewModel[0]);
        this.passengerView = new PassengerView(passengerViewModels);
        mc.switchToView("PassengerTableView");
    }

    /**
     * Retrieves the seat number for a given seat ID.
     *
     * @param seat_id The ID of the seat.
     * @return The seat number as a string.
     */
    public String getSeatNumber(int seat_id) {
        String seat_number = "";
        String seatQuery = "SELECT seat_number FROM seats WHERE seat_id = '" + seat_id + "';";
        try (ResultSet rs = db.executeQuery(seatQuery)) {
            if (rs.next()) {
                seat_number = rs.getString("seat_number");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return seat_number;
    }

    /**
     * Gets the PassengerView associated with this controller.
     *
     * @return The PassengerView instance.
     */
    public PassengerView getPassengerView() {
        return passengerView;
    }

    /**
     * Gets the FlightView associated with this controller.
     *
     * @return The FlightView instance.
     */
    public FlightView getFlightView() {
        return flightView;
    }


}
