package me.krob.menu;

import me.krob.Main;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends Menu {
    private JButton customerLoginButton;
    private JButton staffLogin;
    private JButton viewProductsButton;
    private JPanel mainPanel;

    public MainMenu(Main main) {
        super("Main Menu", main);
        setContentPane(mainPanel);

        // Show customer login menu
        customerLoginButton.addActionListener(e -> {
            dispose();
            main.getCustomerLoginMenu().setVisible(true);
        });

        // Show staff login menu
        staffLogin.addActionListener(e -> {
            dispose();
            main.getStaffLoginMenu().setVisible(true);
        });

        // Show products menu
        viewProductsButton.addActionListener(e -> {
            dispose();
            main.getBrowseProductsMenu().view();
        });
    }
}
