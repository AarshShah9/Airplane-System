
package controller;

import model.role.User;
import utils.Buttons;
import model.flight.Booking;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainController {
    private Database db;
    private static MainController instance;
    private EntryController entryController;
    private LoginController loginController;
    private UserController userController;
    private RegisterController registerController;
    private User user;
    private FlightController flightController;
    private MembershipController membershipController;
    private BookingsController bookingsController;
    private ManageFlightsController manageFlightsController;
    private ManageAircraftsController manageAircraftsController;
    private AdminFlightController adminFlightController;
    private CrewController crewController;
    private AllUsersController allUsersController;

    private AdminPromotionController adminPromotionController;
    private PaymentController paymentController;
    private Booking booking;

    private JFrame mainFrame;
    private JPanel navPanel;

    private MainController() {
        this.db = Database.getInstance("jdbc:mysql://localhost:3306/airline", "root", "SagittariusA5290$");

        mainFrame = new JFrame("Airline Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600); // Set a default size or pack after adding components
        mainFrame.setLayout(new BorderLayout());

        this.user = new User();
        this.switchToView("EntryView");
        mainFrame.setVisible(true);
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void createNavPanel() {
        initializeNavPanel();
        mainFrame.add(navPanel, BorderLayout.WEST);
    }

    public void removeNavPanel() {
        try{
            mainFrame.remove(navPanel);
        } catch (Exception e){
        }
    }

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

    private void createAdminNavButtons(){
        // Create and add buttons

        Dimension buttonSize = new Dimension(140, 40); // Uniform size for all buttons
        Dimension spacerSize = new Dimension(0, 10); // Spacer size for padding

        // Styling options
        Color buttonColor = new Color(100, 181, 246); // Example color
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        JButton btnUserView = Buttons.createStyledButton(
                "User Profile",
                "UserView",
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnManageFlightView = Buttons.createStyledButton(
                "Manage Flights",
                "ManageFlightsView", //create manage flight view for admin
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnManageAircraftView = Buttons.createStyledButton(
                "Manage Aircrafts",
                "ManageAircraftsView", //create manage aircraft view for admin
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnAdminFlightView = Buttons.createStyledButton(
                "Flights",
                "AdminFlightView", //create manage crews view for admin
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnAllUsersView = Buttons.createStyledButton(
                "All Users",
                "AllUsersView", //create all users view for admin
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnAdminPromotionView = Buttons.createStyledButton(
                "Promotion",
                "AdminPromotionView", //create promotion view for admin
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        navPanel.add(btnUserView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnManageFlightView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnManageAircraftView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnAdminFlightView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnAllUsersView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnAdminPromotionView);
    }

    private void createMemberNavButtons(){

        // Create and add buttons

        Dimension buttonSize = new Dimension(140, 40); // Uniform size for all buttons
        Dimension spacerSize = new Dimension(0, 10); // Spacer size for padding

        // Styling options
        Color buttonColor = new Color(100, 181, 246); // Example color
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        // ... Add other buttons for different views
        JButton btnUserView = Buttons.createStyledButton(
                "User Profile",
                "UserView",
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnFlightView = Buttons.createStyledButton(
                "Flights",
                "FlightView",
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnMembershipView = Buttons.createStyledButton(
                "Membership",
                "MembershipView",
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        navPanel.add(btnUserView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnFlightView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnMembershipView);
    }

    private void createGuestNavButtons(){
            // Create and add buttons

        Dimension buttonSize = new Dimension(140, 40); // Uniform size for all buttons
        Dimension spacerSize = new Dimension(0, 10); // Spacer size for padding

        // Styling options
        Color buttonColor = new Color(100, 181, 246); // Example color
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        JButton btnEntryView = Buttons.createStyledButton(
                "Entry",
                "EntryView",
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnFlightView = Buttons.createStyledButton(
                "Flights",
                "FlightView",
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        navPanel.add(btnEntryView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnFlightView);

    }

    private void createAgentNavButtons(){
        // Create and add buttons

        Dimension buttonSize = new Dimension(140, 40); // Uniform size for all buttons
        Dimension spacerSize = new Dimension(0, 10); // Spacer size for padding

        // Styling options
        Color buttonColor = new Color(100, 181, 246); // Example color
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        JButton btnUserView = Buttons.createStyledButton(
                "User Profile",
                "UserView",
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        JButton btnPassengerListView = Buttons.createStyledButton(
                "Passenger List",
                "PassengerListView", //create passenger list view for agent
                buttonSize,
                buttonColor,
                buttonFont,
                e -> switchToView(e.getActionCommand()));

        navPanel.add(btnUserView);
        navPanel.add(Box.createRigidArea(spacerSize));
        navPanel.add(btnPassengerListView);
    }

    public void switchToViewWithArgs(String viewName, Map<String, Object> args) {
        Container contentPane = mainFrame.getContentPane();
        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        Component centerComponent = layout.getLayoutComponent(BorderLayout.CENTER);

        if (centerComponent != null) {
            contentPane.remove(centerComponent);
        }

        switch (viewName) {
            case "SeatMapView":
                break;
            
            case "CrewView":
                crewController = new CrewController(db, this, args);
                mainFrame.getContentPane().add(crewController.getView());
                break;

            default:
                break;
        }

        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }



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

            case "AdminPromotionView":
                adminPromotionController = new AdminPromotionController(db, this);
                mainFrame.getContentPane().add(adminPromotionController.getView());

            case "PaymentView":
                paymentController = new PaymentController(db, this, booking);
                mainFrame.getContentPane().add(paymentController.getView());
                break;

            default:
                break;
        }

        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }

}