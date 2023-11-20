package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EntryView extends JPanel {
    private JButton Admin = new JButton("Admin");
    private JButton Member = new JButton("Member");
    private JButton Agent = new JButton("Agent");
    private JButton unReg = new JButton("Guest");

    /**
     * Default constructor for main view
     */
    public EntryView() {
        setBackground(new Color(230, 230, 250));
        // setTitle("Main Menu");
        setLayout(null);
        setSize(450, 300);

        Admin.setBounds(66, 57, 115, 56);
        add(Admin);
        Admin.setActionCommand("Admin");
        Member.setBounds(242, 57, 115, 56);
        add(Member);
        Member.setActionCommand("Member");
        Agent.setBounds(66, 141, 115, 56);
        add(Agent);
        Agent.setActionCommand("Agent");
        unReg.setBounds(242, 141, 115, 56);
        add(unReg);
        unReg.setActionCommand("Guest");

        JLabel optionsLbl = new JLabel("Please select one of the options");
        optionsLbl.setBounds(96, 16, 222, 20);
        add(optionsLbl);

        JLabel exitLbl = new JLabel("Or click X to exit");
        exitLbl.setBounds(154, 213, 126, 20);
        add(exitLbl);
    }

    /**
     * Setter for action listener for adding a Admin event
     * 
     * @param al action listener to trigger adding a Admin
     */
    public void addAdmin(ActionListener al) {
        Admin.addActionListener(al);
    }

    /**
     * Setter for action listener for adding a Member event
     * 
     * @param al action listener to trigger adding a Member
     */
    public void addMember(ActionListener al) {
        Member.addActionListener(al);
    }

    /**
     * Setter for action listener for adding a Agent event
     * 
     * @param al action listener to trigger adding a Agent
     */
    public void addAgent(ActionListener al) {
        Agent.addActionListener(al);
    }

    /**
     * action listener for adding a guest
     * 
     * @param al action listener
     */
    public void addGuest(ActionListener al) {
        unReg.addActionListener(al);
    }

}
