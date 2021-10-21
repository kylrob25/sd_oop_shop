package me.krob.storage.dao;

import javafx.animation.Animation;
import me.krob.model.product.Product;
import me.krob.model.product.products.Clothing;
import me.krob.model.product.products.Footwear;
import me.krob.model.user.users.Customer;
import me.krob.storage.DAO;
import me.krob.storage.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO extends DAO<Integer, Product> {
    public ProductDAO(DatabaseManager manager) {
        super(manager, "products");
    }

    /**
     * Filling our data map
     */
    public void load() {
        // Auto-closing our connection by using a try statement
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("ProductId");
                    String productName = resultSet.getString("ProductName");
                    double price = resultSet.getDouble("Price");
                    int stockLevel = resultSet.getInt("StockLevel");
                    String measurement = resultSet.getString("Measurement");
                    int size = resultSet.getInt("Size");

                    Product product;

                    // Checking whether our product is a piece of clothing or footwear
                    if (measurement == null || measurement.isEmpty()) {
                        product = new Clothing(productId, productName, price, stockLevel, measurement);
                    } else {
                        product = new Footwear(productId, productName, price, stockLevel, size);
                    }

                    // Storing our product in the data map
                    dataMap.put(productId, product);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
