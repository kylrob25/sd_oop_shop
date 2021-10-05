package me.krob.storage.dao;

import me.krob.model.user.users.Customer;
import me.krob.storage.DAO;
import me.krob.storage.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO extends DAO<String, Customer> {
    public CustomerDAO(DatabaseManager manager) {
        super(manager, "customers");
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
                    String username = resultSet.getString("Username");
                    String password = resultSet.getString("Password");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String addressLine1 = resultSet.getString("AddressLine1");
                    String addressLine2 = resultSet.getString("AddressLine2");
                    String town = resultSet.getString("Town");
                    String postcode = resultSet.getString("Postcode");

                    // Creating and storing a new customer in our data map
                    Customer customer = new Customer(username, password, firstName,
                            lastName, addressLine1, addressLine2, town, postcode, true);
                    dataMap.put(username, customer);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}