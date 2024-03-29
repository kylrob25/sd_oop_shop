package me.krob.menu;

import me.krob.Main;
import me.krob.model.order.Order;
import me.krob.model.order.OrderLine;
import me.krob.model.product.Product;
import me.krob.storage.DatabaseManager;
import me.krob.storage.dao.OrderDAO;
import me.krob.storage.dao.OrderLineDAO;
import me.krob.storage.dao.ProductDAO;
import me.krob.util.TableUtil;
import me.krob.util.model.BasketTableModel;

import javax.swing.*;

public class ViewBasketMenu extends Menu {
    private static final String CONFIRMATION =
            "<html> " +
            "Thank you for your purchase %s! <br>" +
            " Order Date: %s <br>" +
            " Order Cost: £%.2f " +
            "</html>";
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

        basketTable.getSelectionModel().addListSelectionListener(e -> {
            int row = basketTable.getSelectedRow();

            // This is only called when the user selects a row, we don't need to check for -1

            OrderLine line = order.getLineByIndex(row);
            int quantity = line.getQuantity();
            int amountToRemove = (int) quantityField.getValue();

            // Ensuring the removal cannot be higher than the selected quantity
            if (amountToRemove > quantity) {
                quantityField.setValue(1);
            }
        });

        quantityField.setValue(1);

        quantityField.addChangeListener(e -> {
            int row = basketTable.getSelectedRow();

            // Ensuring a row is selected
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select an order to remove first!");
                return;
            }

            // Grabbing the line using our index list
            OrderLine line = order.getLineByIndex(row);
            int quantity = line.getQuantity();
            int next = (int) quantityField.getNextValue();

            // Ensuring you can't remove more quantity than there is
            if (next > quantity) {
                quantityField.setValue(quantity);
            } else if (next < 1) {
                quantityField.setValue(1);
            }
        });

        removeSelectedButton.addActionListener(e -> {
            int selectedRow = basketTable.getSelectedRow();

            // Ensuring a row is selected
            if (selectedRow == -1 || selectedRow >= order.getLineCount()) {
                updateDisplay("Please select an order to remove first!");
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

            Product product = line.getProduct();

            // Seamlessly updating the products page spinner
            main.getBrowseProductsMenu().getQuantityField().setValue(product.getStockLevel());

            // Returning the stock
            line.returnProduct(amountToRemove);

            // Updating model
            basketTable.repaint();

            updateDisplay(String.format("Removed x%s %s from the basket!", amountToRemove, line.getProduct().getName()));
            updateTotal(order);
        });

        purchaseButton.addActionListener(e -> {
            if (order.isEmpty()) {
                updateDisplay("Your basket is empty!");
                return;
            }

            order.updateDate(System.currentTimeMillis());
            order.setStatus("Complete");

            DatabaseManager databaseManager = main.getDatabaseManager();
            OrderDAO orderDAO = databaseManager.getOrderDAO();
            if (!orderDAO.insert(order)) {
                updateDisplay("Failed to purchase!");
                return;
            }

            main.getUserSession().getOrders().addFirst(order);

            OrderLineDAO orderLineDAO = databaseManager.getOrderLineDAO();
            ProductDAO productDAO = databaseManager.getProductDAO();

            // Iterating over each line
            order.getLines().forEach(line -> {
                // Inserting the line into the database
                if (!orderLineDAO.insert(line)) {
                    updateDisplay("Failed to insert line!");
                    return;
                }

                // Updating the product stock in the database
                Product product = line.getProduct();
                if (!productDAO.modify(product, "StockLevel", product.getStockLevel())) {
                    updateDisplay("Failed to modify product!");
                }
            });

            createConfirmationPage(order);
        });
    }

    public void createConfirmationPage(Order order) {
        // Show confirmation
        JLabel label = new JLabel(String.format(CONFIRMATION, order.getUsername(), order.getDate(), order.getTotal()));
        JOptionPane.showMessageDialog(null, label, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);

        dispose(); // Hide this
        main.getBrowseProductsMenu().completeOrder(); // Clean up
        main.getCustomerHomeMenu().setVisible(true); // Show home menu
    }

    public void updateTotal(Order order) {
        totalLabel.setText(String.format(TOTAL, order.calculateTotal()));
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }
}
