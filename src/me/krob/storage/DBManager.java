package me.krob.storage;

import me.krob.storage.dao.CustomerDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
    private static final String CONNECTION = "jdbc:ucanaccess://data/ShopDB.accdb";

    private final CustomerDAO customerDAO = new CustomerDAO(this);

    /**
     * Loading the Driver
     */
    public void loadDriver() {
        try {
            Class.forName(DRIVER);
            System.out.println("Loaded UCanAccess Driver.");
        } catch (ClassNotFoundException exception) {
            System.out.println("Failed to load UCanAccess Driver.");
            System.exit(0);
        }
    }

    /**
     * Fetching a new connection
     * @return - the connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION);
    }
}
