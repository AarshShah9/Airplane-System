package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Setup Decorator Pattern
public class LoginView extends JFrame{
    //Text fields for username and password
    private JTextField username;
    private JPasswordField password;
    private JButton loginBtn;
    private JButton registerBtn;

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        getContentPane().setBackground(new Color(230, 230, 250));

        // Using BorderLayout for overall layout
        setLayout(new BorderLayout(10, 10));
        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4); // Padding

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        username = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(username, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        password = new JPasswordField(10);
        gbc.gridx = 1;
        formPanel.add(password, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Login Button
        loginBtn = new JButton("Login");
        buttonPanel.add(loginBtn);

        // Register Button
        registerBtn = new JButton("Register");
        buttonPanel.add(registerBtn);

        return buttonPanel;
    }

    public String getUsername(){
        return username.getText();
    }

    public String getPassword(){
        return password.getPassword().toString();
    }

    public void addLoginListener(ActionListener al){
        loginBtn.addActionListener(al);
    }

    public void addRegisterListener(ActionListener al){
        registerBtn.addActionListener(al);
    }

    public void display() {
        System.out.println("Login Form:");
        // Code to display login form (username and password fields)
    }
}