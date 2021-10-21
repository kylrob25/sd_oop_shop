package me.krob.menu;

import me.krob.Main;
import me.krob.model.product.Product;
import me.krob.model.product.products.Clothing;
import me.krob.model.product.products.Footwear;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class BrowseProductsMenu extends JFrame {
    private static final ListModel<String> CATEGORY_MODEL = new AbstractListModel<String>() {
        public int getSize() {
            return 2;
        }

        public String getElementAt(int index) {
            switch (index) {
                case 0:
                    return "Footwear";
                case 1:
                    return "Clothing";
            }
            return null;
        }
    };

    private final Main main;

    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel buttonPanel;
    private JButton addToBasketButton;
    private JButton viewBasketButton;
    private JButton backButton;
    private JList<String> categoryList;
    private JList<String> productsList;
    private JTextField quantityField;

    public BrowseProductsMenu(Main main) {
        super("Browse Products");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();

        // Setting the category model
        categoryList.setModel(CATEGORY_MODEL);

        backButton.addActionListener(e -> {
            // Hiding menu
            dispose();
            // Showing customer home menu
            main.getCustomerHomeMenu().setVisible(true);
        });

        categoryList.addListSelectionListener(e -> {
            // Selected category
            int index = categoryList.getSelectedIndex();
            // Updating model based on the selected category
            productsList.setModel(getProductListModel(index));
        });
    }

    public ListModel<String> getProductListModel(int index) {
        /**
         * We are collection the products and filtering out the products that come under
         * the selected category, then we are using the product names to map the list.
         */
        List<String> products = main.getDatabaseManager().getProductDAO().getValues().stream()
                .filter(product -> index == 0 ? product instanceof Clothing : index == 1 && product instanceof Footwear)
                .map(Product::getName).collect(Collectors.toList());

        return new AbstractListModel<String>() {
            public int getSize() {
                return products.size();
            }

            public String getElementAt(int index) {
                return products.get(index);
            }
        };
    }
}
