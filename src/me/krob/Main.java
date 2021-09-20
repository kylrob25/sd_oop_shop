package me.krob;

import me.krob.menu.CustomerLoginMenu;
import me.krob.storage.DBManager;
import me.krob.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    private final DBManager dbManager;

    private final MainMenu mainMenu;
    private final CustomerLoginMenu customerLoginMenu;

    public Main() {
        dbManager = new DBManager();
        dbManager.loadDriver();

        mainMenu = new MainMenu(this);
        mainMenu.setVisible(true);
        customerLoginMenu = new CustomerLoginMenu(this);
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public CustomerLoginMenu getCustomerLoginMenu() {
        return customerLoginMenu;
    }
}
