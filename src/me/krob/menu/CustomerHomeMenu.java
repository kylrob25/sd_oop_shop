package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.users.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerHomeMenu extends UserMenu<Customer> {
    private JPanel mainPanel;
    private JButton browseProductsButton;
    private JButton viewOrdersButton;
    private JButton logoutButton;
    private JLabel greetingLabel;

    public CustomerHomeMenu(Main main) {
        super("Customer Home", main);
        setContentPane(mainPanel);
        logoutButton.addActionListener(e -> logout());
    }

    public void setGreetingText(String text) {
        greetingLabel.setText(text);
    }
}
