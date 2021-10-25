package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.User;

public abstract class HomeMenu extends Menu {
    public HomeMenu(String title, Main main) {
        super(title, main);
    }

    public void login(User user) {
        // Setting user
        main.getUserSession().setUser(user);
        // Setting label
        setGreetingText(user.getDisplayGreeting());
        // Showing menu
        setVisible(true);
    }

    public void logout() {
        // Nulling user
        main.getUserSession().setUser(null);
        // Clearing label
        setGreetingText(null);
        // Hiding menu
        dispose();
        // Showing Main menu
        main.getMainMenu().setVisible(true);
    }

    public abstract void setGreetingText(String text);
}
