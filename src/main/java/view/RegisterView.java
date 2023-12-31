package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The RegisterView class represents the view for user registration.
 */
public class RegisterView extends JPanel {
    private JTextField emailField;
    private JTextField usernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JPasswordField passwordField;
    private JButton registerBtn;
    private JButton cancelBtn;

    /**
     * Constructs a new RegisterView.
     */
    public RegisterView() {
        setSize(450, 350);
        setBackground(new Color(230, 230, 250));

        // Using BorderLayout for overall layout
        setLayout(new BorderLayout(10, 10));
        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Creates the form panel for user registration.
     *
     * @return The form panel containing user registration fields.
     */
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4); // Padding

        // Email
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // First Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        // Last Name
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Address:"), gbc);
        addressField = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(10);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);


        return formPanel;
    }

    /**
     * Creates the button panel for user registration.
     *
     * @return The button panel containing registration and cancellation buttons.
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Register Button
        registerBtn = new JButton("Register");
        buttonPanel.add(registerBtn);

        // Cancel Button
        cancelBtn = new JButton("Cancel");
        buttonPanel.add(cancelBtn);

        return buttonPanel;
    }

    /**
     * Gets the user's email input.
     *
     * @return The email entered by the user.
     */
    public String getEmail() { // need to check if email is valid
        try {
            return emailField.getText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Gets the user's username input.
     *
     * @return The username entered by the user.
     */
    public String getUsername() {
        try {
            return usernameField.getText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Gets the user's password input.
     *
     * @return The password entered by the user.
     */
    public String getPassword() {
        try {
            return new String(passwordField.getPassword());
        } catch (NullPointerException e) {
            return null;
        }
    }

    
    public String getFirstName() {
        try {
            return firstNameField.getText();
        } catch (NullPointerException e) {
            return null;
        }
    }


    public String getLastName() {
        try {
            return lastNameField.getText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getAddress() {
        try {
            return addressField.getText();
        } catch (NullPointerException e) {
            return null;
        }
    }



    /**
     * Adds an ActionListener for the registration button.
     *
     * @param al The ActionListener to be added for the registration button.
     */
    public void addRegisterListener(ActionListener al) {
        registerBtn.addActionListener(al);
    }

    /**
     * Adds an ActionListener for the cancel button.
     *
     * @param al The ActionListener to be added for the cancel button.
     */
    public void addCancelListener(ActionListener al) {
        cancelBtn.addActionListener(al);
    }

    /**
     * Displays an error message dialog.
     *
     * @param error The error message to be displayed.
     */
    public void addError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a success message dialog.
     *
     * @param success The success message to be displayed.
     */
    public void addSuccess(String success) {
        JOptionPane.showMessageDialog(this, success, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
