package me.krob.menu;

import me.krob.Main;
import me.krob.model.order.Order;
import me.krob.model.order.OrderLine;
import me.krob.util.TableUtil;
import me.krob.util.model.BasketTableModel;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ViewBasketMenu extends Menu {
    private static final ScheduledExecutorService SERVICE = Executors.newSingleThreadScheduledExecutor();
    private static final String TOTAL = "Total: £%.2f";

    private JPanel mainPanel;
    private JTable basketTable;
    private JButton backButton;
    private JButton removeSelectedButton;
    private JSpinner quantityField;
    private JLabel displayLabel;
    private JButton purchaseButton;
    private JLabel totalLabel;

    public ViewBasketMenu(Main main, Order order) {
        super("View Basket", main);
        setContentPane(mainPanel);

        basketTable.setModel(new BasketTableModel(order));
        TableUtil.setCentreRenderer(basketTable);

        backButton.addActionListener(e -> {
            dispose();

            main.getBrowseProductsMenu().view();
        });

        quantityField.setValue(1);

        quantityField.addChangeListener(e -> {
            int selectedRow = basketTable.getSelectedRow();

            // Ensuring a row is selected
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select an order to remove first!");
                return;
            }

            // Grabbing the line using our index list
            OrderLine selectedLine = order.getLineByIndex(selectedRow);
            int selectedQuantity = selectedLine.getQuantity();
            int next = (int) quantityField.getNextValue();

            // Ensuring you can't remove more quantity than there is
            if (next > selectedQuantity) {
                quantityField.setValue(selectedQuantity);
            } else if (next < 1) {
                quantityField.setValue(1);
            }
        });

        removeSelectedButton.addActionListener(e -> {
            int selectedRow = basketTable.getSelectedRow();

            // Ensuring a row is selected
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select an order to remove first!");
                return;
            }

            // Grabbing the line using our index list
            OrderLine line = order.getLineByIndex(selectedRow);
            int quantity = line.getQuantity();
            int amountToRemove = (int) quantityField.getValue();

            if (quantity == amountToRemove) {
                // Removing the whole order line
                order.removeOrderLine(line);
            } else {
                // Removing the desired quantity
                line.removeQuantity(amountToRemove);
            }

            // Updating model
            basketTable.repaint();

            updateDisplay(String.format("Removed x%s %s from the basket!", amountToRemove, line.getProduct().getName()));
            updateTotal(order);
        });
    }

    public void updateTotal(Order order) {
        totalLabel.setText(String.format(TOTAL, order.calculateTotal()));
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }
}
