package main.java.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.java.viewModel.FlightViewModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FlightView extends JPanel {

    private JTable flightTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private ActionListener flightSelectionListener;
    private FlightViewModel flightViewModel[];

    public FlightView(FlightViewModel fvm[]) {
        // setTitle("Flights");
        flightViewModel = fvm;

        setSize(600, 400);
        setLayout(null);

        JLabel lbl = new JLabel("Flights");
        lbl.setBounds(250, 10, 100, 30);
        add(lbl);

        // Define column names
        String[] columnNames = { "Flight Number", "Depature Time", "Arrival Time", "Price", "Depature", "Destination" };

        // Initialize the table model and set column names
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadFlights();

        flightTable = new JTable(tableModel);

        // Mouse listener for row selection
        flightTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = flightTable.getSelectedRow();
                    if (flightSelectionListener != null) {
                        flightSelectionListener.actionPerformed(
                                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(row)));
                    }
                }
            }
        });

        scrollPane = new JScrollPane(flightTable);
        scrollPane.setBounds(50, 50, 500, 300);
        add(scrollPane);
    }

    public void addFlightSelectionListener(ActionListener listener) {
        this.flightSelectionListener = listener;
    }

    private void loadFlights() {
        for (int i = 0; i < flightViewModel.length; i++) {
            tableModel.addRow(new Object[] { flightViewModel[i].FlightNumber, flightViewModel[i].DepartureTime,
                    flightViewModel[i].ArrivalTime, flightViewModel[i].Price,
                    flightViewModel[i].Departure, flightViewModel[i].Destination });
        }
    }
}
