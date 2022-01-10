package me.krob.menu;

import me.krob.Main;
import me.krob.model.order.Order;
import me.krob.model.order.OrderLine;
import me.krob.model.product.Product;
import me.krob.session.UserSession;

import javax.swing.*;

public class BrowseProductsMenu extends Menu {
    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel buttonPanel;
    private JButton addToBasketButton;
    private JButton viewBasketButton;
    private JButton backButton;
    private JList<String> categoryList;
    private JList<Product> productsList;
    private JSpinner quantityField;
    private JLabel quantityText;
    private JLabel displayLabel;

    private ViewBasketMenu basketMenu;
    private Order order;

    private boolean ignoreQuantityChange;

    public BrowseProductsMenu(Main main) {
        super("Browse Products", main);
        setContentPane(mainPanel);

        // Setting the category model
        categoryList.setModel(main.getCategoryListModel());

        // Setting the default selected value
        categoryList.setSelectedIndex(0);

        // Setting default product model
        productsList.setModel(new DefaultListModel<>());

        // Initially updating the product model
        updateProductListModel();

        // Setting the default selected product
        productsList.setSelectedIndex(0);

        // Initially setting the quantity spinner
        quantityField.setValue(1);

        backButton.addActionListener(e -> {
            // Showing a menu depending on if they are logged in or not
            UserSession session = main.getUserSession();
            if (session.isActive()) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Are you sure? Your basket will be cleared!", "Warning!", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    dispose(); // Hiding menu
                    main.getCustomerHomeMenu().setVisible(true); // Showing customer menu
                    clearOrder(); // Clearing the order
                }
            } else {
                // Hiding menu
                dispose();

                // Showing the main menu
                main.getMainMenu().setVisible(true);
            }
        });

        categoryList.addListSelectionListener(e -> {
            // Selected category
            int index = categoryList.getSelectedIndex();
            // Updating model based on the selected category
            updateProductListModel();
            // Setting the default selected product
            productsList.setSelectedIndex(0);
        });

        productsList.addListSelectionListener(e -> {
            // Resetting spinner when we click a new item
            quantityField.setValue(1);
        });

        quantityField.addChangeListener(e -> {
            Product product = productsList.getSelectedValue();

            int next = (int) quantityField.getNextValue();
            int previous = (int) quantityField.getPreviousValue();

            // Making sure the value cannot be below 0
            if (product == null || (next < 1 || previous < 1)) {
                quantityField.setValue(1);
                return;
            }

            int stockLevel = product.getStockLevel();

            // Making sure the value cannot be larger than the available stock
            if (next > stockLevel) {
                quantityField.setValue(stockLevel);
            }
        });

        addToBasketButton.addActionListener(e -> {
            // This should never run as we always set default selected value
            if (productsList.getSelectedIndex() == -1) {
                updateDisplay("Please select a product!");
                return;
            }

            Product product = productsList.getSelectedValue();
            int quantity = (int) quantityField.getValue();
            int stock = product.getStockLevel();

            int newStock = stock - quantity;

            if (newStock < 0) {
                updateDisplay(String.format("Not enough stock! (Stock: %s)", stock));
                return;
            }

            // Adjust stock level
            product.setStockLevel(newStock);

            OrderLine orderLine = new OrderLine(product, quantity);
            order.tryOrderLine(orderLine);
            updateDisplay(String.format("Added %s x%s to the basket for £%.2f!", product.getName(), quantity, product.getPrice() * quantity));
        });

        viewBasketButton.addActionListener(e -> {
            // Hiding menu
            dispose();

            // Showing menu
            basketMenu.updateTotal(order);
            basketMenu.setVisible(true);
        });
    }

    public void completeOrder() {
        clearOrder(false);
    }

    public void clearOrder() {
        clearOrder(true);
    }

    private void clearOrder(boolean stock) {
        if (stock) order.getLines().forEach(OrderLine::returnProduct); // Returning stock
        order = null; // Nulling our order
        basketMenu = null; // Nulling our basket
    }

    private void updateProductListModel() {
        DefaultListModel<Product> model = (DefaultListModel<Product>) productsList.getModel();
        String selectedValue = categoryList.getSelectedValue();

        model.clear();

        main.getDatabaseManager().getProductDAO().getValues().stream()
                .filter(product -> product.getClass().getSimpleName().equals(selectedValue))
                .forEach(model::addElement);
    }

    public void view() {
        setVisible(true);

        UserSession session = main.getUserSession();
        boolean active = session.isActive();

        addToBasketButton.setVisible(active);
        viewBasketButton.setVisible(active);
        quantityField.setVisible(active);
        quantityText.setVisible(active);

        // We only create a new order if they don't already have one
        if (active && order == null) {
            order = new Order();
            order.setUsername(session.getUsername());

            basketMenu = new ViewBasketMenu(main, order);
        }
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }

    public JSpinner getQuantityField() {
        return quantityField;
    }
}
