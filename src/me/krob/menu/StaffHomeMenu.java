package me.krob.menu;

import me.krob.Main;

import javax.swing.*;

public class StaffHomeMenu extends HomeMenu {
    private JPanel mainPanel;
    private JButton modifyProductsButton;
    private JButton viewOrdersButton;
    private JButton logoutButton;
    private JLabel greetingLabel;

    public StaffHomeMenu(Main main) {
        super("Staff Home", main);
        setContentPane(mainPanel);

        logoutButton.addActionListener(e -> logout());

        modifyProductsButton.addActionListener(e -> {
            // Hiding menu
            dispose();

            // Showing modify products menu
            main.getModifyProductsMenu().setVisible(true);
        });
    }

    public void setGreetingText(String text) {
        greetingLabel.setText(text);
    }
}
