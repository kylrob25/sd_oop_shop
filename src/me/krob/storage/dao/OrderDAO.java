package me.krob.storage.dao;

import me.krob.model.order.Order;
import me.krob.storage.DAO;
import me.krob.storage.DatabaseManager;

import java.sql.*;

public class OrderDAO extends DAO<Integer, Order> {

    public OrderDAO(DatabaseManager manager) {
        super(manager);
    }

    /**
     * Filling our data map
     */
    public void load() {
        // Auto-closing our connection by using a try statement
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Orders")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("OrderId");
                    Date date = new Date(resultSet.getTimestamp("OrderDate").getTime());
                    String username = resultSet.getString("Username");
                    double total = resultSet.getDouble("OrderTotal");
                    String status = resultSet.getString("Status");

                    // Creating and storing a new customer in our data map
                    Order order = new Order(id, date, total, status);
                    order.setUsername(username);

                    dataMap.put(id, order);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
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

                        order.setId(orderId); // Setting the proper ID
                        order.getLines().forEach(line -> line.setOrderId(orderId)); // Setting the ID for each line
                        dataMap.put(orderId, order); // Storing our order
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
