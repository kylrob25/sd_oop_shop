package me.krob.util.model;

import me.krob.model.order.Order;
import me.krob.session.UserSession;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private static final String[] COLUMN_NAMES = {"ID", "Date", "Price", "Status"};
    private static final Class<?>[] COLUMN_CLASS = {Integer.class, String.class, Double.class, String.class};

    private final List<Order> orders = new ArrayList<>();

    public List<Order> loadOrders(UserSession session) {
        orders.clear(); // Clearing orders
        orders.addAll(session.getOrders()); // Adding our orders
        return orders;
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
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (!orders.isEmpty()) {
            Order order = orders.get(rowIndex);

            if (order != null) {
                switch (columnIndex) {
                    case 0:
                        return order.getId();
                    case 1:
                        return DateFormat.getInstance().format(order.getDate());
                    case 2:
                        return String.format("£%.2f", order.getTotal());
                    case 3:
                        return order.getStatus();
                }
            }
        }
        return null;
    }
}
