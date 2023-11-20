
package main.java.controller;

import main.java.model.role.User;

import javax.swing.*;
import java.awt.*;

public class MainController {
    private Database db;
    private static MainController instance;
    private EntryController entryController;
    private LoginController loginController;
    private UserController userController;
    private RegisterController registerController;
    private User user;
    private FlightController flightController;

    private JFrame mainFrame;
    private JPanel navPanel;

    private MainController() {
        this.db = Database.getInstance("jdbc:mysql://localhost:3306/airline", "root", "SagittariusA5290$");

        mainFrame = new JFrame("Airline Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600); // Set a default size or pack after adding components
        mainFrame.setLayout(new BorderLayout());

        // Initialize navigation panel
        initializeNavPanel();

        // Add navigation panel to the main frame
        mainFrame.add(navPanel, BorderLayout.WEST);

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

    private void initializeNavPanel() {
        navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setPreferredSize(new Dimension(150, mainFrame.getHeight())); // Set preferred width

        // Create and add buttons
        JButton btnEntryView = new JButton("Entry");
        btnEntryView.setActionCommand("EntryView");
        btnEntryView.addActionListener(e -> switchToView(e.getActionCommand()));

        JButton btnFlightView = new JButton("Flights");
        btnFlightView.setActionCommand("FlightView");
        btnFlightView.addActionListener(e -> switchToView(e.getActionCommand()));

        // ... Add other buttons for different views

        navPanel.add(btnEntryView);
        navPanel.add(btnFlightView);
        // ... Add other buttons to the panel
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

            default:
                break;
        }

        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }

}
