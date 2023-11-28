package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Setup Decorator Pattern
public class PaymentView extends JPanel {
    private JTextField cardNumber;
    private JTextField expirationDate;
    private JTextField cvv;
    private JButton confirmBtn;
    private JButton yesButton;
    private JLabel messageLabel; // Label to display messages

    public PaymentView() {
        // setTitle("Login");
        setSize(450, 300);
        setBackground(new Color(230, 230, 250));

        // Using BorderLayout for overall layout
        setLayout(new BorderLayout(10, 10));
        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        add(createMessagePanel(), BorderLayout.NORTH); // Add message panel at the top
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4); // Padding

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Card Number:"), gbc);
        cardNumber = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(cardNumber, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Expiration Date:"), gbc);
        expirationDate = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(expirationDate, gbc);

                // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("CVV:"), gbc);
        cvv = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(cvv, gbc);

            // Cancellation Insurance Message
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Would you like cancellation insurance?"), gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Login Button
        confirmBtn = new JButton("Confirm Booking");
        buttonPanel.add(confirmBtn);

        
        JButton yesButton = new JButton("Yes");
        buttonPanel.add(yesButton);

        return buttonPanel;
    }

    private JPanel createMessagePanel() {
        JPanel messagePanel = new JPanel();
        messageLabel = new JLabel(" ");
        messagePanel.add(messageLabel);
        return messagePanel;
    }


    public JButton getYesButton() {
        return yesButton;
    }


    public void addConfirmListener(ActionListener al) {
        confirmBtn.addActionListener(al);
    }



    public void display() {
        System.out.println("Payment Page:");
        // Code to display login form (username and password fields)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentView());
    }
}
