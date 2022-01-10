package me.krob.util.model;

import me.krob.model.order.Order;
import me.krob.session.UserSession;
import me.krob.storage.dao.OrderDAO;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private static final DateFormat DATE_FORMAT = DateFormat.getInstance();

    private static final String[] COLUMN_NAMES = {"ID", "Customer", "Date", "Price"};
    private static final Class<?>[] COLUMN_CLASS = {Integer.class, String.class, String.class, Double.class};

    private final List<Order> orders = new ArrayList<>();

    public void loadOrders(UserSession session) {
        orders.clear(); // Clearing orders
        orders.addAll(session.getOrders()); // Adding our orders
    }

    public void loadAllOrders(OrderDAO orderDAO) {
        orders.clear();
        orders.addAll(orderDAO.getValues());
    }

    public List<Order> getOrders() {
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
                        return order.getUsername();
                    case 2:
                        return DATE_FORMAT.format(order.getDate());
                    case 3:
                        return String.format("£%.2f", order.getTotal());
                }
            }
        }
        return null;
    }
}
