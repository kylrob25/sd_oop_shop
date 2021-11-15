package me.krob.menu;

import me.krob.Main;
import me.krob.model.product.Product;
import me.krob.model.product.products.Clothing;
import me.krob.model.product.products.Footwear;
import me.krob.storage.dao.ProductDAO;

import javax.swing.*;
import java.util.Arrays;

import static me.krob.util.StringUtil.isEmpty;

public class EditProductMenu extends Menu {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JSpinner idField;
    private JTextField nameField;
    private JTextField priceField;
    private JSpinner stockField;
    private JTextField extraField;
    private JButton backButton;
    private JLabel extraLabel;
    private JButton editNameButton;
    private JButton editPriceButton;
    private JButton editStockButton;
    private JButton editExtraButton;

    // The product we're editing
    private Product product;

    public EditProductMenu(Main main) {
        super("Edit Product", main);
        setContentPane(mainPanel);

        backButton.addActionListener(e -> {
            // Hiding this menu
            dispose();

            // Clearing fields
            clearFields();

            // Clearing product
            product = null;

            // Showing products menu
            main.getModifyProductsMenu().setVisible(true);
        });

        stockField.addChangeListener(e -> {
            // Ensuring the stock cannot be below 0
            if ((int) stockField.getNextValue() < 0) {
                stockField.setValue(0);
            }
        });

        editNameButton.addActionListener(e -> {
            String name = nameField.getText();

            // Ensuring name field is not empty
            if (isEmpty(name)) {
                JOptionPane.showMessageDialog(null, "Please ensure the name field is not empty!");
                return;
            }

            // Prevent pointless updates
            String oldName = product.getName();
            if (oldName.equals(name)) {
                JOptionPane.showMessageDialog(null, "Please ensure product names are different!");
                return;
            }

            // Modifying database
            ProductDAO productDAO = main.getDatabaseManager().getProductDAO();
            if (!productDAO.modify(product, "ProductName", name)) {
                JOptionPane.showMessageDialog(null, "Failed to modify product name!");
                return;
            }

            // Setting name
            product.setName(name);
            JOptionPane.showMessageDialog(null, String.format("New Product Name! (%s -> %s)", oldName, name));
        });

        editPriceButton.addActionListener(e -> {
            String priceString = priceField.getText();

            // Ensuring field is not empty
            if (isEmpty(priceString)) {
                JOptionPane.showMessageDialog(null, "Please ensure the price field is not empty!");
                return;
            }

            // Price validation
            double price;
            try {
                price = Double.parseDouble(priceString);
            } catch (Throwable throwable) {
                priceField.setText(null);
                JOptionPane.showMessageDialog(null, "Please ensure the price is valid!");
                return;
            }

            // Prevent pointless updates
            double oldPrice = product.getPrice();
            if (oldPrice == price) {
                JOptionPane.showMessageDialog(null, "Please ensure product prices are different!");
                return;
            }

            // Modifying database
            ProductDAO productDAO = main.getDatabaseManager().getProductDAO();
            if (!productDAO.modify(product, "Price", price)) {
                JOptionPane.showMessageDialog(null, "Failed to modify product price!");
                return;
            }

            // Setting price
            product.setPrice(price);
            JOptionPane.showMessageDialog(null, String.format("New Product Price! (£%.2f -> £%.2f)", oldPrice, price));
        });

        editStockButton.addActionListener(e -> {
            int stock = (int) stockField.getValue();
            int oldStock = product.getStockLevel();

            // Prevent pointless updates
            if (oldStock == stock) {
                JOptionPane.showMessageDialog(null, "Please ensure product stock levels are different!");
                return;
            }

            // Modifying database
            ProductDAO productDAO = main.getDatabaseManager().getProductDAO();
            if (!productDAO.modify(product, "StockLevel", stock)) {
                JOptionPane.showMessageDialog(null, "Failed to modify product stock!");
                return;
            }

            // Setting new stock level
            product.setStockLevel(stock);
            JOptionPane.showMessageDialog(null, String.format("New Product Stock Level! (%s -> %s)", oldStock, stock));
        });

        editExtraButton.addActionListener(e -> {
            if (product instanceof Footwear) {
                Footwear footwear = (Footwear) product;
                String sizeString = extraLabel.getText();

                // Size validation
                int size;
                try {
                    size = Integer.parseInt(sizeString);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    extraField.setText(null);
                    JOptionPane.showMessageDialog(null, "Please ensure the size is valid!");
                    return;
                }

                // Prevent pointless updates
                int oldSize = footwear.getSize();
                if (oldSize == size) {
                    extraField.setText(null);
                    JOptionPane.showMessageDialog(null, "Please ensure footwear sizes are different!");
                    return;
                }

                // Modifying database
                ProductDAO productDAO = main.getDatabaseManager().getProductDAO();
                if (!productDAO.modify(footwear, "Size", size)) {
                    JOptionPane.showMessageDialog(null, "Failed to modify footwear size!");
                    return;
                }

                // Setting size
                footwear.setSize(size);
                JOptionPane.showMessageDialog(null, String.format("New Footwear Size! (%s -> %s)", oldSize, size));
                return;
            }

            Clothing clothing = (Clothing) product;
            String measurement = extraField.getText();
            ProductDAO productDAO = main.getDatabaseManager().getProductDAO();
            String oldMeasurement = clothing.getMeasurement();

            // Preventing pointless updates
            if (oldMeasurement.equals(measurement)) {
                extraField.setText(null);
                JOptionPane.showMessageDialog(null, "Please ensure clothing measurements' are different!");
                return;
            }

            // Modifying database
            if (!productDAO.modify(clothing, "Measurement", measurement)) {
                JOptionPane.showMessageDialog(null, "Failed to modify clothing measurement!");
                return;
            }

            // Setting measurement
            clothing.setMeasurement(measurement);
            JOptionPane.showMessageDialog(null, String.format("New Clothing Measurement! (%s -> %s)", oldMeasurement, measurement));
        });
    }

    public void view(Product product) {
        // Setting the active product
        this.product = product;

        // Setting fields
        setFields(product);

        // Showing the menu
        setVisible(true);
    }

    private void setFields(Product product) {
        idField.setValue(product.getId());
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        stockField.setValue(product.getStockLevel());

        // Updating the extra label/field accordingly depending on the kind of Product
        if (product instanceof Footwear) {
            Footwear footwear = (Footwear) product;
            extraLabel.setText("Size");
            extraField.setText(String.valueOf(footwear.getSize()));
            return;
        }

        Clothing clothing = (Clothing) product;
        extraLabel.setText("Measurement");
        extraField.setText(clothing.getMeasurement());
    }

    private void clearFields() {
        // Clearing text fields
        Arrays.asList(
                nameField, priceField, extraField
        ).forEach(field -> field.setText(null));

        // Clearing spinner fields
        Arrays.asList(
                idField, stockField
        ).forEach(field -> field.setValue(0));
    }
}
