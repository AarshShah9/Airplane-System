package main.java.controller;

import main.java.model.role.User;
import main.java.view.LoginView;
import java.awt.event.*;

import javax.swing.Action;

//Will need to add listener when login view is created
public class LoginController implements ActionListener {
    private User model;
    private LoginView view;
    private Database db;
    private MainController mainController;

    public LoginController(Database db, MainController mc) {
        this.mainController = mc;
        this.model = mainController.getUser();
        this.view = new LoginView();
        this.db = db;
        addListeners();
    }

    public LoginView getView() {
        return view;
    }

    private void addListeners() {
        view.addLoginListener(this);
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

    public boolean authenticate() {
        String query = "SELECT * FROM users WHERE username = '" + model.getUsername() + "' AND password = '"
                + model.getPassword() + "' AND role <= " + model.getRole();
        return db.executeQuery(query) != null;
    }

    public void login() {
        if (authenticate()) {
            System.out.println("Login successful");
            updateView();
            // this.uc = new UserController(db);
            // this.uc.setUser(model);
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public void logout() {
        setUsername("");
        setPassword("");
        updateView();
    }

    public void register() {
        // check if user already exists
        String query = "SELECT * FROM user WHERE username = '" + model.getUsername() + "'";
        if (db.executeQuery(query) != null) {
            System.out.println("User already exists");
        } else {
            // insert user into database
            try {
                query = "INSERT INTO user (username, password, email) VALUES ('" + model.getUsername() + "', '"
                        + model.getPassword() + "', '" + model.getEmail() + "')";
                db.executeUpdate(query);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            // this.uc = new UserController(db);
            // this.uc.setUser(model);
            System.out.println("User registered successfully");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action: " + e.getActionCommand());
        // setEmail(loginView.getEmail()); //might not receive email from login view,
        // will need to check
        try {
            if (e.getActionCommand().equals("Login")) {
                setPassword(view.getPassword());
                setUsername(view.getUsername());
                login();
            } else if (e.getActionCommand().equals("Register")) { // probably will need to change this
                mainController.switchToView("RegisterView");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateView() {
        view.display();
    }
}
