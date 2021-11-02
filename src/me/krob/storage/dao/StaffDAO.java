package me.krob.storage.dao;

import me.krob.model.user.users.Staff;
import me.krob.storage.DAO;
import me.krob.storage.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDAO extends DAO<String, Staff> {
    public StaffDAO(DatabaseManager manager) {
        super(manager, "staff");
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
                    String position = resultSet.getString("Position");
                    double salary = resultSet.getDouble("Salary");

                    // Creating and storing a new staff member in our data map
                    Staff staff = new Staff(username, password, firstName, lastName, position, salary);
                    dataMap.put(username, staff);
                    System.out.println(username + " " + password);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean insert(Staff value) {
        return false;
    }
}
