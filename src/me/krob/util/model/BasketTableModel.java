package me.krob.util.model;

import me.krob.model.order.Order;
import me.krob.model.order.OrderLine;
import me.krob.model.product.Product;

import javax.swing.table.AbstractTableModel;

public class BasketTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"ID", "Name", "Price", "Quantity"};
    private static final Class<?>[] COLUMN_CLASS = {Integer.class, String.class, Double.class, Integer.class};

    private final Order order;

    public BasketTableModel(Order order) {
        this.order = order;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_CLASS[columnIndex];
    }

    @Override
    public int getRowCount() {
        return order.getLines().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (order != null) {
            OrderLine line = order.getLineByIndex(rowIndex);

            if (line != null) {
                Product product = line.getProduct();
                switch (columnIndex) {
                    case 0:
                        return line.getId();
                    case 1:
                        return product.getName();
                    case 2:
                        return product.getPrice();
                    case 3:
                        return line.getQuantity();
                }
            }
        }
        return null;
    }
}
