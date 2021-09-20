package me.krob.menu;

import me.krob.Main;

import javax.swing.*;
import java.awt.*;

public class CustomerLoginMenu extends JFrame {
    private final Main main;

    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton mainMenuButton;
    private JTextField usernameField;
    private JTextField passwordField;

    public CustomerLoginMenu(Main main) {
        super("Customer Login");
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();

        mainMenuButton.addActionListener(e -> {
            this.setVisible(false);
            main.getMainMenu().setVisible(true);
        });
    }
}
