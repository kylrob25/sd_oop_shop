package me.krob.menu;

import me.krob.Main;
import me.krob.model.product.Product;

import javax.swing.*;

public class ModifyProductsMenu extends Menu {
    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel buttonPanel;
    private JButton backButton;
    private JList<String> categoryList;
    private JList<Product> productsList;
    private JButton editButton;
    private JButton deleteButton;

    public ModifyProductsMenu(Main main) {
        super("Modify Products", main);
        setContentPane(mainPanel);

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
            // Showing customer home menu
            main.getStaffHomeMenu().setVisible(true);
        });

        categoryList.addListSelectionListener(e -> {
            // Selected category
            int index = categoryList.getSelectedIndex();
            // Updating model based on the selected category
            updateProductListModel();
            // Setting the default selected product
            productsList.setSelectedIndex(0);
        });
    }

    public void updateProductListModel() {
        main.getModelUtil().updateProductListModel(
                (DefaultListModel<Product>) productsList.getModel(),
                categoryList.getSelectedValue()
        );
    }
}
