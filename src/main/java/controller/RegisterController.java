package main.java.controller;

import main.java.model.role.User;
import main.java.view.RegisterView;
import java.awt.event.*;

import javax.swing.Action;
import java.sql.ResultSet;
import java.sql.SQLException;

//Will need to add listener when login view is created
public class RegisterController implements ActionListener {
    private User model;
    private RegisterView view;
    private Database db;
    private MainController mainController;

    public RegisterController(Database db, MainController mc) {
        this.mainController = mc;
        this.model = mainController.getUser();
        this.view = new RegisterView();
        this.db = db;
        addListeners();
    }

    public RegisterView getView() {
        return view;
    }

    private void addListeners() {
        view.addCancelListener(this);
        view.addRegisterListener(this);
    }

    public void setUsername(String username) {
        model.setUsername(username);
    }

    public void setPassword(String password) {
        model.setPassword(password);
    }

    public void setEmail(String email) {
        model.setEmail(email);
    }

    public void register() {
        // check if user already exists
        String query = "SELECT * FROM users WHERE username = '" + model.getUsername() + "'";
        ResultSet rs = db.executeQuery(query);

        // check if query was null or returned nothing
        try {
            if (rs != null && rs.next()) {
                System.out.println("User already exists"); // Maybe make this an exception
            } else {
                // insert user into database
                try {
                    query = "INSERT INTO users (username, password, email, role) VALUES ('" + model.getUsername()
                            + "', '"
                            + model.getPassword() + "', '" + model.getEmail() + "', '" + model.getRole() + "')";
                    db.executeUpdate(query);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                // this.uc = new UserController(db);
                // this.uc.setUser(model);
                System.out.println("User registered successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action: " + e.getActionCommand());
        setPassword(view.getPassword());
        setUsername(view.getUsername());
        setEmail(view.getEmail());
        try {
            if (e.getActionCommand().equals("Register")) { // probably will need to change this
                register();
                mainController.switchToView(null);
            }
            if (e.getActionCommand().equals("Cancel")) {
                mainController.switchToView(null);
                ;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
