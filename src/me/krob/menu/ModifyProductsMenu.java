package me.krob.menu;

import me.krob.Main;
import me.krob.model.product.Product;
import me.krob.storage.dao.ProductDAO;

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
        categoryList.setModel(main.getCategoryListModel());

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

        editButton.addActionListener(e -> {
            // Hiding this menu
            dispose();

            // Showing edit product menu
            Product product = productsList.getSelectedValue();
            main.getEditProductMenu().view(product);
        });

        deleteButton.addActionListener(e -> {
            ProductDAO productDAO = main.getDatabaseManager().getProductDAO();
            Product product = productsList.getSelectedValue();
            int productId = product.getId();

            if (!productDAO.delete(productId)) {
                JOptionPane.showMessageDialog(null, String.format("Failed to delete product! (ID=%s)", productId));
                return;
            }

            updateProductListModel();
            productsList.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, String.format("Deleted Product! (ID=%s)", productId));
        });
    }

    public void updateProductListModel() {
        DefaultListModel<Product> model = (DefaultListModel<Product>) productsList.getModel();
        String selectedValue = categoryList.getSelectedValue();

        model.clear();

        main.getDatabaseManager().getProductDAO().getValues().stream()
                .filter(product -> product.getClass().getSimpleName().equals(selectedValue))
                .forEach(model::addElement);
    }
}
