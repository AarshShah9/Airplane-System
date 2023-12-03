
package controller;

import model.role.User;
import utils.Buttons;
import model.flight.Booking;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * The MainController class is responsible for managing the main functionality of the Airline Management System.
 * It controls the navigation between different views, user authentication, and database operations.
 */
/**
 * The MainController class is responsible for managing the main functionality of the Airline Management System.
 * It controls the navigation between different views and handles user interactions.
 */
public class MainController {
    // Instance variables
    private Database db;
    private static MainController instance;
    private EntryController entryController;
    private LoginController loginController;
    private UserController userController;
    private RegisterController registerController;
    private User user;
    private Booking userBooking;
    private AgentController agentController;
    private FlightController flightController;
    private SeatController seatController;
    private MembershipController membershipController;
    private BookingsController bookingsController;
    private ManageFlightsController manageFlightsController;
    private ManageAircraftsController manageAircraftsController;
    private ManageLocationsController manageLocationsController;
    private AdminFlightController adminFlightController;
    private CrewController crewController;
    private AllUsersController allUsersController;
    private PaymentController paymentController;
    private AdminPromotionController adminPromotionController;
    private JFrame mainFrame;
    private JPanel navPanel;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the database connection, sets up the main frame, and switches to the EntryView.
     */
    private MainController(String Url, String username, String password) {
        this.db = Database.getInstance();
        this.db.connect(Url, username, password);

        mainFrame = new JFrame("Airline Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600); // Set a default size or pack after adding components
        mainFrame.setLayout(new BorderLayout());

        this.user = new User();
        this.switchToView("EntryView");
        mainFrame.setVisible(true);
    }

    /**
        * Gets a singleton instance of the MainController class.
        * @param Url The database URL.
        * @param username The database username.
        * @param password The database password.
        * @return The MainController instance.
     **/
    public static MainController getInstance(String Url, String username, String password) {
        if (instance == null) {
            instance = new MainController(Url, username, password);
        }
        return instance;
    }

    /**
     * Sets the current user.
     *
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the current user.
     *
     * @return The current user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Creates and adds the navigation panel to the main frame.
     */
    public void createNavPanel() {
        initializeNavPanel();
        mainFrame.add(navPanel, BorderLayout.WEST);
    }

    /**
     * Removes the navigation panel from the main frame.
     */
    public void removeNavPanel() {
        try {
            mainFrame.remove(navPanel);
        } catch (Exception e) {
            // Handle exception
        }
    }

    /**
     * Initializes the navigation panel based on the user's role.
     */
    private void initializeNavPanel() {
        navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setPreferredSize(new Dimension(140, mainFrame.getHeight())); // Set preferred width

        switch (user.getRole()) {
            case 4:
                createAdminNavButtons();
                break;
            case 3:
                createAgentNavButtons();
                break;
            case 2:
                createMemberNavButtons();
                break;
            case 1:
                createGuestNavButtons();
                break;
            default:
                break;
        }
    }

/**
 * Creates and adds navigation buttons for the admin panel.
 * These buttons include options for managing user profiles, flights, aircraft, locations,
 * and other administrative tasks.
 */
private void createAdminNavButtons() {
    // Create and add buttons
    
    // Define the uniform size for all buttons
    Dimension buttonSize = new Dimension(140, 40);
    // Define spacer size for padding between buttons
    Dimension spacerSize = new Dimension(0, 10);

    // Define styling options for the buttons
    Color buttonColor = new Color(100, 181, 246); // Example color
    Font buttonFont = new Font("Arial", Font.BOLD, 14);

    // Create and style the "User Profile" button
    JButton btnUserView = Buttons.createStyledButton(
            "User Profile",
            "UserView",
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Manage Flights" button
    JButton btnManageFlightView = Buttons.createStyledButton(
            "Manage Flights",
            "ManageFlightsView", // create manage flight view for admin
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Manage Aircrafts" button
    JButton btnManageAircraftView = Buttons.createStyledButton(
            "Manage Aircrafts",
            "ManageAircraftsView", // create manage aircraft view for admin
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Manage Locations" button
    JButton btnManageLocationsView = Buttons.createStyledButton(
            "Manage Locations",
            "ManageLocationsView", // create manage locations view for admin
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Flights" button
    JButton btnAdminFlightView = Buttons.createStyledButton(
            "Flights",
            "AdminFlightView", // create manage crews view for admin
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "All Users" button
    JButton btnAllUsersView = Buttons.createStyledButton(
            "All Users",
            "AllUsersView", // create all users view for admin
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Promotion" button
    JButton btnAdminPromotionView = Buttons.createStyledButton(
            "Promotion",
            "AdminPromotionView", // create promotion view for admin
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Add buttons and spacer between them to the navigation panel
    navPanel.add(btnUserView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnManageFlightView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnManageAircraftView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnManageLocationsView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnAdminFlightView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnAllUsersView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnAdminPromotionView);
}


/**
 * Creates and adds navigation buttons for the member panel.
 * These buttons include options for viewing user profiles, flights, and membership information.
 */
private void createMemberNavButtons() {
    // Create and add buttons
    
    // Define the uniform size for all buttons
    Dimension buttonSize = new Dimension(140, 40);
    // Define spacer size for padding between buttons
    Dimension spacerSize = new Dimension(0, 10);

    // Define styling options for the buttons
    Color buttonColor = new Color(100, 181, 246); // Example color
    Font buttonFont = new Font("Arial", Font.BOLD, 14);

    // Create and style the "User Profile" button
    JButton btnUserView = Buttons.createStyledButton(
            "User Profile",
            "UserView",
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Flights" button
    JButton btnFlightView = Buttons.createStyledButton(
            "Flights",
            "FlightView",
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Membership" button
    JButton btnMembershipView = Buttons.createStyledButton(
            "Membership",
            "MembershipView",
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Add buttons and spacer between them to the navigation panel
    navPanel.add(btnUserView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnFlightView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnMembershipView);
}

/**
 * Creates and adds navigation buttons for the guest panel.
 * These buttons include options for entering the application and viewing available flights.
 */
private void createGuestNavButtons() {
    // Create and add buttons
    
    // Define the uniform size for all buttons
    Dimension buttonSize = new Dimension(140, 40);
    // Define spacer size for padding between buttons
    Dimension spacerSize = new Dimension(0, 10);

    // Define styling options for the buttons
    Color buttonColor = new Color(100, 181, 246); // Example color
    Font buttonFont = new Font("Arial", Font.BOLD, 14);

    // Create and style the "Entry" button
    JButton btnEntryView = Buttons.createStyledButton(
            "Entry",
            "EntryView",
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Flights" button
    JButton btnFlightView = Buttons.createStyledButton(
            "Flights",
            "FlightView",
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Add buttons and spacer between them to the navigation panel
    navPanel.add(btnEntryView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnFlightView);
}

/**
 * Creates and adds navigation buttons for the agent panel.
 * These buttons include options for viewing user profiles and the passenger list.
 */
private void createAgentNavButtons() {
    // Create and add buttons
    
    // Define the uniform size for all buttons
    Dimension buttonSize = new Dimension(140, 40);
    // Define spacer size for padding between buttons
    Dimension spacerSize = new Dimension(0, 10);

    // Define styling options for the buttons
    Color buttonColor = new Color(100, 181, 246); // Example color
    Font buttonFont = new Font("Arial", Font.BOLD, 14);

    // Create and style the "User Profile" button
    JButton btnUserView = Buttons.createStyledButton(
            "User Profile",
            "UserView",
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Create and style the "Passenger List" button
    JButton btnPassengerListView = Buttons.createStyledButton(
            "Passenger List",
            "PassengerListView", // create passenger list view for agent
            buttonSize,
            buttonColor,
            buttonFont,
            e -> switchToView(e.getActionCommand()));

    // Add buttons and spacer between them to the navigation panel
    navPanel.add(btnUserView);
    navPanel.add(Box.createRigidArea(spacerSize));
    navPanel.add(btnPassengerListView);
}


/**
 * Switches the current view based on the provided view name and optional arguments.
 *
 * @param viewName The name of the view to switch to.
 * @param args     Optional arguments to pass to the view controller.
 * @throws IllegalArgumentException if args is null.
 */
public void switchToViewWithArgs(String viewName, Map<String, Object> args) {

    if (args == null) {
        throw new IllegalArgumentException("args cannot be null");
    }

    Container contentPane = mainFrame.getContentPane();
    BorderLayout layout = (BorderLayout) contentPane.getLayout();
    Component centerComponent = layout.getLayoutComponent(BorderLayout.CENTER);

    if (centerComponent != null) {
        contentPane.remove(centerComponent);
    }

    switch (viewName) {
        case "SeatMapView":
            seatController = new SeatController(db, this, args);
            mainFrame.getContentPane().add(seatController.getView());
            break;

        case "CrewView":
            crewController = new CrewController(db, this, args);
            mainFrame.getContentPane().add(crewController.getView());
            break;

        case "PaymentView":
            paymentController = new PaymentController(db, this, args);
            mainFrame.getContentPane().add(paymentController.getView());
            break;

        default:
            break;
    }

    mainFrame.getContentPane().revalidate();
    mainFrame.getContentPane().repaint();
}

/**
 * Switches the current view based on the provided view name.
 *
 * @param viewName The name of the view to switch to.
 */
public void switchToView(String viewName) {

    Container contentPane = mainFrame.getContentPane();
    BorderLayout layout = (BorderLayout) contentPane.getLayout();
    Component centerComponent = layout.getLayoutComponent(BorderLayout.CENTER);

    if (centerComponent != null) {
        contentPane.remove(centerComponent);
    }

    switch (viewName) {
        case "EntryView":
            entryController = new EntryController(db, this);
            mainFrame.getContentPane().add(entryController.getView());
            break;

        case "FlightView":
            flightController = new FlightController(db, this);
            mainFrame.getContentPane().add(flightController.getView());
            break;

        case "LoginView":
            loginController = new LoginController(db, this);
            mainFrame.getContentPane().add(loginController.getView());
            break;

            case "RegisterView":
                registerController = new RegisterController(db, this);
                mainFrame.getContentPane().add(registerController.getView());
                break;
            case "UserView":
                userController = new UserController(db, this);
                mainFrame.getContentPane().add(userController.getView());
                break;

            case "MembershipView":
                membershipController = new MembershipController(db, this);
                mainFrame.getContentPane().add(membershipController.getView());
                break;

            case "BookingsView":
                bookingsController = new BookingsController(db, this);
                mainFrame.getContentPane().add(bookingsController.getView());
                break;

            case "ManageFlightsView":
                manageFlightsController = new ManageFlightsController(db, this);
                mainFrame.getContentPane().add(manageFlightsController.getView());
                break;

            case "ManageFlightView":
                mainFrame.getContentPane().add(manageFlightsController.getMFView());
                break;

            case "ManageAircraftsView":
                manageAircraftsController = new ManageAircraftsController(db, this);
                mainFrame.getContentPane().add(manageAircraftsController.getView());
                break;

            case "AddAircraftView":
                mainFrame.getContentPane().add(manageAircraftsController.getAddAircraftView());
                break;

            case "AdminFlightView":
                adminFlightController = new AdminFlightController(db, this);
                mainFrame.getContentPane().add(adminFlightController.getView());
                break;

            case "AllUsersView":
                allUsersController = new AllUsersController(db, this);
                mainFrame.getContentPane().add(allUsersController.getView());
                break;
            case "PassengerListView":
                agentController = new AgentController(db, this);
                mainFrame.getContentPane().add(agentController.getFlightView());
                break;
            case "PassengerTableView":
                mainFrame.getContentPane().add(agentController.getPassengerView());
                break;

            case "AdminPromotionView":
                adminPromotionController = new AdminPromotionController(db, this);
                mainFrame.getContentPane().add(adminPromotionController.getView());
                break;

            case "ManageLocationsView":
                manageLocationsController = new ManageLocationsController(db, this);
                mainFrame.getContentPane().add(manageLocationsController.getView());
                break;

            default:
                break;
        }

        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }

}