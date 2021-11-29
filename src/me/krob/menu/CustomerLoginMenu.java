package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.users.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class CustomerLoginMenu extends Menu {
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton mainMenuButton;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel displayLabel;

    public CustomerLoginMenu(Main main) {
        super("Customer Login", main);
        setContentPane(mainPanel);

        mainMenuButton.addActionListener(e -> {
            // Cleaning up this menu
            passwordField.setText(null);
            usernameField.setText(null);
            dispose();

            // Showing main menu
            main.getMainMenu().setVisible(true);
        });

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            Customer customer = main.getDatabaseManager().getCustomerDAO().get(username);

            // Couldn't find a customer with the provided username
            if (customer == null) {
                updateDisplay("Incorrect username...");
                passwordField.setText(null);
                usernameField.setText(null);
                return;
            }

            // Passwords do not match
            if (!password.equals(customer.getPassword())) {
                updateDisplay("Incorrect password...");
                passwordField.setText(null);
                usernameField.setText(null);
                return;
            }

            // Cleaning up this menu
            passwordField.setText(null);
            usernameField.setText(null);
            dispose();

            // Showing customer home menu
            main.getCustomerHomeMenu().login(customer);
        });

        registerButton.addActionListener(e -> {
            // Cleaning up this menu
            passwordField.setText(null);
            usernameField.setText(null);
            dispose();

            // Showing registration menu
            main.getRegistrationMenu().setVisible(true);
        });
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }
}
