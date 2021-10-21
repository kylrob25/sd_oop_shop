package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.User;

import javax.swing.*;
import java.awt.*;

public abstract class UserMenu<U extends User> extends JFrame {
    private final Main main;

    public UserMenu(String title, Main main) {
        super(title);
        this.main = main;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 250));
        setResizable(false);
        pack();
    }

    public void login(U user) {
        // Setting user
        main.getUserContainer().setUser(user);
        // Setting label
        setGreetingText(user.getDisplayGreeting());
        // Showing menu
        setVisible(true);
    }

    public void logout() {
        // Nulling user
        main.getUserContainer().setUser(null);
        // Clearing label
        setGreetingText(null);
        // Hiding menu
        dispose();
        // Showing Main menu
        main.getMainMenu().setVisible(true);
    }
    public abstract void setGreetingText(String text);
}
