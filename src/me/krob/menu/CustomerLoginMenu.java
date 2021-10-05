package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.users.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerLoginMenu extends JFrame {
    private final Main main;

    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton mainMenuButton;
    private JPasswordField passwordField;
    private JTextField usernameField;

    public CustomerLoginMenu(Main main) {
        super("Customer Login");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();

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

            Customer customer = main.getDbManager().getCustomerDAO().get(username);

            // Couldn't find a customer with the provided username
            if (customer == null) {
                JOptionPane.showMessageDialog(null, "Invalid username...");
                passwordField.setText(null);
                usernameField.setText(null);
                return;
            }

            // Passwords do not match
            if (!password.equals(customer.getPassword())) {
                JOptionPane.showMessageDialog(null, "Incorrect password...");
                usernameField.setText(null);
                return;
            }

            // Cleaning up this menu
            passwordField.setText(null);
            usernameField.setText(null);
            dispose();

            // Showing customer home menu
            main.getCustomerHomeMenu().setVisible(true);
        });
    }
}
