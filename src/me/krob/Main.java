package me.krob;

import me.krob.menu.*;
import me.krob.storage.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    private final DatabaseManager databaseManager;

    private final MainMenu mainMenu;
    private final CustomerLoginMenu customerLoginMenu;
    private final StaffLoginMenu staffLoginMenu;
    private final CustomerHomeMenu customerHomeMenu;
    private final StaffHomeMenu staffHomeMenu;

    public Main() {
        databaseManager = new DatabaseManager();
        databaseManager.loadDriver();
        databaseManager.loadDAO();

        mainMenu = new MainMenu(this);
        mainMenu.setVisible(true);

        customerLoginMenu = new CustomerLoginMenu(this);
        staffLoginMenu = new StaffLoginMenu(this);
        customerHomeMenu = new CustomerHomeMenu(this);
        staffHomeMenu = new StaffHomeMenu(this);
    }

    public DatabaseManager getDbManager() {
        return databaseManager;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public CustomerLoginMenu getCustomerLoginMenu() {
        return customerLoginMenu;
    }

    public StaffLoginMenu getStaffLoginMenu() {
        return staffLoginMenu;
    }

    public CustomerHomeMenu getCustomerHomeMenu() {
        return customerHomeMenu;
    }

    public StaffHomeMenu getStaffHomeMenu() {
        return staffHomeMenu;
    }
}
