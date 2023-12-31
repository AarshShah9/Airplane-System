package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The MembershipView class represents the view for managing membership options.
 */
public class MembershipView extends JPanel {
    private JButton cancelMembership;
    private JButton signUp;

    /**
     * Constructs a new MembershipView.
     *
     * @param member Indicates whether the user is already a member.
     */
    public MembershipView(boolean member) {
        setSize(450, 350);
        setBackground(new Color(230, 230, 250));
        setLayout(new BorderLayout(10, 10));

        if (member) {
            // Display a button to cancel membership
            cancelMembership = new JButton("Cancel Membership");
            cancelMembership.setBackground(new Color(255, 255, 255));
            cancelMembership.setForeground(new Color(0, 0, 0));
            cancelMembership.setFont(new Font("SansSerif", Font.PLAIN, 20));
            cancelMembership.setPreferredSize(new Dimension(200, 100));
            add(cancelMembership, BorderLayout.CENTER);
        } else {
            // Display a button to sign up for membership
            signUp = new JButton("Sign Up");
            signUp.setBackground(new Color(255, 255, 255));
            signUp.setForeground(new Color(0, 0, 0));
            signUp.setFont(new Font("SansSerif", Font.PLAIN, 20));
            signUp.setPreferredSize(new Dimension(200, 100));
            add(signUp, BorderLayout.CENTER);
        }
    }

    /**
     * Gets the "Cancel Membership" button.
     *
     * @return The "Cancel Membership" button.
     */
    public JButton getCancelMembership() {
        return cancelMembership;
    }

    /**
     * Gets the "Sign Up" button.
     *
     * @return The "Sign Up" button.
     */
    public JButton getSignUp() {
        return signUp;
    }
}
