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
    private JButton submitButton;
    private JButton backButton;
    private JLabel extraLabel;

    public EditProductMenu(Main main) {
        super("Edit Product", main);
        setContentPane(mainPanel);

        backButton.addActionListener(e -> {
            // Hiding this menu
            dispose();

            // Clearing fields
            clearFields();

            // Showing products menu
            main.getModifyProductsMenu().setVisible(true);
        });

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String priceString = priceField.getText();
            String extra = extraField.getText();

            // Ensuring no fields are empty
            if (isEmpty(name) || isEmpty(priceString) || isEmpty(extra)) {
                JOptionPane.showMessageDialog(null, "Please ensure all fields are not empty!");
                return;
            }

            // Validating price
            double price;
            try {
                price = Double.parseDouble(priceString);
            } catch (Throwable throwable) {
                priceField.setText(null);
                JOptionPane.showMessageDialog(null, "Please ensure you have entered a valid price!");
                return;
            }

            ProductDAO productDAO = main.getDatabaseManager().getProductDAO();
            // TODO:
        });
    }

    public void view(Product product) {
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
