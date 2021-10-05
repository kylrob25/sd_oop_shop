package me.krob.menu;

import me.krob.Main;

import javax.swing.*;
import java.awt.*;

public class CustomerHomeMenu extends JFrame {
    private final Main main;

    private JPanel mainPanel;
    private JButton browseProductsButton;
    private JButton viewOrdersButton;
    private JButton logoutButton;

    public CustomerHomeMenu(Main main) {
        super("Customer Home");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();

        logoutButton.addActionListener(e -> {
            dispose();
            main.getMainMenu().setVisible(true);
        });
    }
}
