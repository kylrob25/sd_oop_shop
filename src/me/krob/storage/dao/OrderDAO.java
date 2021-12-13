package me.krob.storage.dao;

import me.krob.model.order.Order;
import me.krob.storage.DAO;
import me.krob.storage.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO extends DAO<Integer, Order> {

    public OrderDAO(DatabaseManager manager) {
        super(manager);
    }

    public void load() {

    }

    /**
     * Insert an Order into the database
     * @param order - the order to be stored
     * @return - whether the order was stored or not
     */
    public boolean insert(Order order) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Orders" +
                     " (OrderId, OrderDate, Username, OrderTotal, Status) VALUES (?, ?, ?, ?, ?)")) {

            statement.setInt(1, order.getId());
            statement.setDate(2, order.getDate());
            statement.setString(3, order.getUsername());
            statement.setDouble(4, order.getTotal());
            statement.setString(5, order.getStatus());

            if (!statement.execute()) {
                // If an order is successful we must set the line order ids
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int orderId = resultSet.getInt(1);
                        order.getLines().forEach(line -> line.setOrderId(orderId));
                    }
                }
                return true;
            }

            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public boolean modify(Order value, String field, Object obj) {
        return false;
    }

    public boolean delete(Integer key) {
        return false;
    }
}
