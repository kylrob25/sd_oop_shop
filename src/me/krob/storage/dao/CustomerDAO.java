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
        super(manager);
    }

    /**
     * Filling our data map
     */
    public void load() {
        // Auto-closing our connection by using a try statement
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customers")) {

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
                    System.out.println(username + " " + password);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Insert a customer into the database
     * @param customer - the customer to be stored
     * @return - whether the customer was stored or not
     */
    public boolean insert(Customer customer) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Customers" +
                     " (Username, Password, FirstName, LastName, AddressLine1, AddressLine2, Town, PostCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            String username = customer.getUsername();

            statement.setString(1, username);
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getFirstName());
            statement.setString(4, customer.getLastName());
            statement.setString(5, customer.getAddressLine1());
            statement.setString(6, customer.getAddressLine2());
            statement.setString(7, customer.getTown());
            statement.setString(8, customer.getPostcode());

            if (!statement.execute()) {
                dataMap.put(username, customer);
                return true;
            }

            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public boolean modify(Customer customer, String field, Object obj) {
        return false;
    }

    public boolean delete(String key) {
        return false;
    }
}
