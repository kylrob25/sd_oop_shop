package me.krob.storage.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import me.krob.model.order.Order;
import me.krob.model.order.OrderLine;
import me.krob.model.product.Product;
import me.krob.storage.DAO;
import me.krob.storage.DatabaseManager;

import java.sql.*;

public class OrderLineDAO extends DAO<Integer, OrderLine> {

    public OrderLineDAO(DatabaseManager manager) {
        super(manager);
    }

    /**
     * Filling our data map
     */
    public void load() {
        // Auto-closing our connection by using a try statement
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM OrderLines")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                ProductDAO productDAO = manager.getProductDAO();
                while (resultSet.next()) {
                    int id = resultSet.getInt("OrderLineId");
                    int productId = resultSet.getInt("ProductId");
                    int quantity = resultSet.getInt("Quantity");
                    double total = resultSet.getDouble("LineTotal");
                    int orderId = resultSet.getInt("OrderId");

                    Product product = productDAO.get(productId);
                    if (product == null) {
                        System.out.printf("There was an issue loading Line %s because Product %s does not exist.%n", id, productId);
                        return;
                    }

                    OrderLine line = new OrderLine(id, product, quantity, total);
                    line.setOrderId(orderId);

                    dataMap.put(id, line);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
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
