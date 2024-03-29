package me.krob;

import me.krob.session.UserSession;
import me.krob.menu.*;
import me.krob.storage.DatabaseManager;
import me.krob.util.model.CategoryListModel;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    private final DatabaseManager databaseManager;
    private final UserSession userSession;

    private final MainMenu mainMenu;
    private final CustomerLoginMenu customerLoginMenu;
    private final StaffLoginMenu staffLoginMenu;
    private final CustomerHomeMenu customerHomeMenu;
    private final StaffHomeMenu staffHomeMenu;
    private final BrowseProductsMenu browseProductsMenu;
    private final ModifyProductsMenu modifyProductsMenu;
    private final RegistrationMenu registrationMenu;
    private final EditProductMenu editProductMenu;
    private final ViewOrdersMenu viewOrdersMenu;

    private final CategoryListModel categoryListModel;

    public Main() {
        databaseManager = new DatabaseManager();
        databaseManager.loadDriver();
        databaseManager.loadDAO();

        userSession = new UserSession();

        mainMenu = new MainMenu(this);
        mainMenu.setVisible(true);

        categoryListModel = new CategoryListModel();

        customerLoginMenu = new CustomerLoginMenu(this);
        staffLoginMenu = new StaffLoginMenu(this);
        customerHomeMenu = new CustomerHomeMenu(this);
        staffHomeMenu = new StaffHomeMenu(this);
        browseProductsMenu = new BrowseProductsMenu(this);
        modifyProductsMenu = new ModifyProductsMenu(this);
        registrationMenu = new RegistrationMenu(this);
        editProductMenu = new EditProductMenu(this);
        viewOrdersMenu = new ViewOrdersMenu(this);
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public CategoryListModel getCategoryListModel() {
        return categoryListModel;
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

    public BrowseProductsMenu getBrowseProductsMenu() {
        return browseProductsMenu;
    }

    public ModifyProductsMenu getModifyProductsMenu() {
        return modifyProductsMenu;
    }

    public RegistrationMenu getRegistrationMenu() {
        return registrationMenu;
    }

    public EditProductMenu getEditProductMenu() {
        return editProductMenu;
    }

    public ViewOrdersMenu getViewOrdersMenu() {
        return viewOrdersMenu;
    }
}
