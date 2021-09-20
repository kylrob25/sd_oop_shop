package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String CONNECTION = "jdbc:ucanaccess://";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION);
    }
}
