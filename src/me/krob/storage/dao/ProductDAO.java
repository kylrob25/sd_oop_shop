package me.krob.storage.dao;

import me.krob.model.product.Product;
import me.krob.model.product.products.Clothing;
import me.krob.model.product.products.Footwear;
import me.krob.storage.DAO;
import me.krob.storage.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO extends DAO<Integer, Product> {
    public ProductDAO(DatabaseManager manager) {
        super(manager);
    }

    /**
     * Filling our data map
     */
    public void load() {
        // Auto-closing our connection by using a try statement
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Products")) {

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
                        product = new Footwear(productId, productName, price, stockLevel, size);
                    } else {
                        product = new Clothing(productId, productName, price, stockLevel, measurement);
                    }

                    // Storing our product in the data map
                    dataMap.put(productId, product);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean insert(Product value) {
        return false;
    }

    /***
     *
     * @param product - the product
     * @param field - the field nae
     * @param obj - the object
     * @return - was the modification successful
     */
    public boolean modify(Product product, String field, Object obj) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Products SET " + field + " = ? WHERE ProductId = ?");) {
            statement.setObject(1, obj);
            statement.setInt(2, product.getId());
            return !statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * Delete a product
     * @param key - the product ID
     * @return - was the removal successful
     */
    public boolean delete(Integer key) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Products WHERE ProductId = ?")) {
            statement.setInt(1, key);

            // Removing data from the map
            if (!statement.execute()) {
                dataMap.remove(key);
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
