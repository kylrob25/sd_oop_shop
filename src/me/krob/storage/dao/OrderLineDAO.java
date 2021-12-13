package me.krob.storage.dao;

import me.krob.model.order.OrderLine;
import me.krob.storage.DAO;
import me.krob.storage.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderLineDAO extends DAO<Integer, OrderLine> {

    public OrderLineDAO(DatabaseManager manager) {
        super(manager);
    }

    public void load() {

    }

    /**
     * Insert an OrderLine into the database
     * @param line - the order line to be stored
     * @return - whether the order line was stored or not
     */
    public boolean insert(OrderLine line) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO OrderLines" +
                     " (OrderLineId, ProductId, Quantity, LineTotal, OrderId) VALUES (?, ?, ?, ?, ?)")) {
            int lineId = line.getId();

            statement.setInt(1, lineId);
            statement.setInt(2, line.getProduct().getId());
            statement.setInt(3, line.getQuantity());
            statement.setDouble(4, line.getTotal());
            statement.setInt(5, line.getOrderId());

            if (!statement.execute()) {
                return true;
            }

            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public boolean modify(OrderLine value, String field, Object obj) {
        return false;
    }

    public boolean delete(Integer key) {
        return false;
    }
}
