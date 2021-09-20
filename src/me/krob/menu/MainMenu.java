package me.krob.menu;

import me.krob.Main;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private final Main main;

    private JButton customerLoginButton;
    private JButton staffLogin;
    private JButton viewProductsButton;
    private JPanel mainPanel;

    public MainMenu(Main main) {
        super("Main Menu");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(300, 200));
        setResizable(false);
        pack();

        customerLoginButton.addActionListener(e -> {
            this.setVisible(false);
            main.getCustomerLoginMenu().setVisible(true);
        });
    }
}
