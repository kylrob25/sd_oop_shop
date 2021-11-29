package me.krob.menu;

import me.krob.Main;

import javax.swing.*;

public class CustomerHomeMenu extends HomeMenu {
    private JPanel mainPanel;
    private JButton browseProductsButton;
    private JButton viewOrdersButton;
    private JButton logoutButton;
    private JLabel greetingLabel;

    public CustomerHomeMenu(Main main) {
        super("Customer Home", main);
        setContentPane(mainPanel);

        logoutButton.addActionListener(e -> logout());

        browseProductsButton.addActionListener(e -> {
            // Hide menu
            dispose();
            // Show browse products menu
            main.getBrowseProductsMenu().view();
        });
    }

    public void setGreetingText(String text) {
        greetingLabel.setText(text);
    }

    public JLabel getDisplayLabel() {
        return null;
    }
}
