package me.krob.menu;

import me.krob.Main;

import javax.swing.*;
import java.awt.*;

public class BrowseProductsMenu extends JFrame {
    private final Main main;

    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel buttonPanel;
    private JButton addToBasketButton;
    private JButton viewBasketButton;
    private JButton backButton;
    private JList categoryList;
    private JList productsList;

    public BrowseProductsMenu(Main main) {
        super("Browse Products");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();

        backButton.addActionListener(e -> {
            // Hiding menu
            dispose();

            // Showing customer home menu
            main.getCustomerHomeMenu().setVisible(true);
        });
    }
}
