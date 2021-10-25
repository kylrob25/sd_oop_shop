package me.krob.menu;

import me.krob.Main;
import me.krob.model.product.Product;
import me.krob.session.UserSession;

import javax.swing.*;
import java.awt.*;

public class BrowseProductsMenu extends JFrame {
    private final Main main;

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

    public BrowseProductsMenu(Main main) {
        super("Browse Products");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();

        // Setting the category model
        categoryList.setModel(main.getModelUtil().getCategoryListModel());

        // Setting the default selected value
        categoryList.setSelectedIndex(0);

        // Setting default product model
        productsList.setModel(new DefaultListModel<>());

        // Initially updating the product model
        updateProductListModel();

        // Setting the default selected product
        productsList.setSelectedIndex(0);

        backButton.addActionListener(e -> {
            // Hiding menu
            dispose();

            // Showing a menu depending on if they are logged in or not
            UserSession session = main.getUserSession();
            if (session.isActive()) {
                main.getCustomerHomeMenu().setVisible(true);
            } else {
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
            quantityField.setValue(0);
        });

        quantityField.addChangeListener(e -> {
            Product product = productsList.getSelectedValue();

            int next = (int) quantityField.getNextValue();
            int previous = (int) quantityField.getPreviousValue();

            // Making sure the value cannot be below 0
            if (product == null || (next < 0 || previous < 0)) {
                quantityField.setValue(0);
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
                JOptionPane.showMessageDialog(null, "Please select a product!");
                return;
            }

            Product product = productsList.getSelectedValue();
            int quantity = (int) quantityField.getValue();
        });
    }

    public void updateProductListModel() {
        main.getModelUtil().updateProductListModel(
                (DefaultListModel<Product>) productsList.getModel(),
                categoryList.getSelectedValue()
        );
    }

    public void view() {
        setVisible(true);

        UserSession session = main.getUserSession();
        boolean active = session.isActive();

        addToBasketButton.setVisible(active);
        viewBasketButton.setVisible(active);
        quantityField.setVisible(active);
        quantityText.setVisible(active);
    }
}
