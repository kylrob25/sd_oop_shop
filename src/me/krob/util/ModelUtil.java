package me.krob.util;

import me.krob.Main;
import me.krob.model.product.Product;

import javax.swing.*;

public class ModelUtil {
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

    public ModelUtil(Main main) {
        this.main = main;
    }

    public void updateProductListModel(DefaultListModel<Product> model, String selectedValue) {
        // Clearing model
        model.clear();

        // Adding the new products
        main.getDatabaseManager().getProductDAO().getValues().stream()
                .filter(product -> product.getClass().getSimpleName().equals(selectedValue))
                .forEach(model::addElement);
    }

    public ListModel<String> getCategoryListModel() {
        return CATEGORY_MODEL;
    }
}
