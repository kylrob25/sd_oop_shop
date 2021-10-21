package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.users.Staff;

import javax.swing.*;
import java.awt.*;

public class StaffHomeMenu extends UserMenu<Staff> {
    private JPanel mainPanel;
    private JButton modifyProductsButton;
    private JButton viewOrdersButton;
    private JButton logoutButton;
    private JLabel greetingLabel;

    public StaffHomeMenu(Main main) {
        super("Staff Home", main);
        setContentPane(mainPanel);
        logoutButton.addActionListener(e -> logout());
    }

    public void setGreetingText(String text) {
        greetingLabel.setText(text);
    }
}
