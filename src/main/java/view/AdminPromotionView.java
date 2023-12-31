package view;

import model.util.Promotion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The AdminPromotionView class represents the view for displaying promotions in an admin interface.
 */
public class AdminPromotionView extends JPanel {
    private JTable promotionsTable;
    private DefaultTableModel tableModel;
    private Promotion[] promotions;
    private JScrollPane scrollPane;
    private JButton createButton;

    /**
     * Creates a new AdminPromotionView with the provided array of Promotion objects.
     * @param promotions An array of Promotion objects representing promotions data.
     */
    public AdminPromotionView(Promotion[] promotions) {
        this.promotions = promotions;
        setSize(600, 400);
        setLayout(null);

        JLabel lbl = new JLabel("Promotions");
        lbl.setBounds(250, 10, 100, 30);
        add(lbl);

        createButton = new JButton("Create New Promotion");
        createButton.setBounds(50, 10, 200, 30);
        add(createButton);

        initTable();
    }

    /**
     * Initializes the promotions table with column names and data.
     */
    private void initTable() {
        // Define column names
        String[] columnNames = { "PromotionId", "Discount", "Price After Discount" };

        // Initialize the table model and set column names
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadPromotions();

        promotionsTable = new JTable(tableModel);
        promotionsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = promotionsTable.getSelectedRow();
                    if (row != -1) {
                        int response = JOptionPane.showConfirmDialog(null,
                                "Do you want to delete this promotion?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            // Fire delete event with row index
                            firePropertyChange("deletePromotion", -1, row);
                        }
                    }
                }
            }
        });

        scrollPane = new JScrollPane(promotionsTable);
        scrollPane.setBounds(50, 50, 500, 300);
        add(scrollPane);
    }

    /**
     * Loads promotion data into the table.
     */
    private void loadPromotions() {
        for (Promotion promotion : promotions) {
            tableModel.addRow(new Object[]{promotion.getPromotionId(), promotion.getDiscount(),
                    promotion.getPriceForDiscount()});
        }
    }

    /**
     * Gets the create button.
     * @return The create button.
     */
    public JButton getCreateButton() {
        return createButton;
    }

    /**
     * Updates the table data with new Promotion objects.
     * @param newPromotions An array of Promotion objects to update the table with.
     */
    public void updateTableData(Promotion[] newPromotions) {
        promotions = newPromotions;
        tableModel.setRowCount(0);
        loadPromotions();
    }
}
