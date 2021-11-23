package me.krob.util.model;

import javax.swing.*;

public class CategoryListModel extends AbstractListModel<String> {
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
}
