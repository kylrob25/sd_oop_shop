package me.krob.storage;

import me.krob.model.product.Product;
import me.krob.storage.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
    private static final String CONNECTION = "jdbc:ucanaccess://data/ShopDB.accdb";

    private final CustomerDAO customerDAO = new CustomerDAO(this);
    private final StaffDAO staffDAO = new StaffDAO(this);
    private final ProductDAO productDAO = new ProductDAO(this);
    private final OrderDAO orderDAO = new OrderDAO(this);
    private final OrderLineDAO orderLineDAO = new OrderLineDAO(this);

    /**
     * Loading UCanAccess Driver
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
     * Loading DAOs
     */
    public void loadDAO() {
        customerDAO.load();
        staffDAO.load();
        productDAO.load();
        orderDAO.load();
        orderLineDAO.load();
    }

    /**
     * Fetching a new connection
     * @return - the connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION);
    }

    /**
     * Customer DAO
     * @return - the customer dao
     */
    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    /**
     * Staff DAO
     * @return - the staff dao
     */
    public StaffDAO getStaffDAO() {
        return staffDAO;
    }

    /**
     * Product DAO
     * @return - the product dao
     */
    public ProductDAO getProductDAO() {
        return productDAO;
    }

    /**
     * Order DAO
     * @return - the order dao
     */
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    /**
     * OrderLine DAO
     * @return - the order line dao
     */
    public OrderLineDAO getOrderLineDAO() {
        return orderLineDAO;
    }
}
