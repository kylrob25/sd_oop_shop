package me.krob.menu;

import me.krob.Main;
import me.krob.model.user.User;
import me.krob.session.UserSession;

import java.util.LinkedList;
import java.util.stream.Collectors;

public abstract class HomeMenu extends Menu {
    public HomeMenu(String title, Main main) {
        super(title, main);
    }

    public void staffLogin(User user) {
        login(user, true);
    }

    public void login(User user) {
        login(user, false);
    }

    private void login(User user, boolean staff) {
        UserSession session = main.getUserSession();

        session.setStaff(staff);
        session.setUser(user); // Setting user

        if (!staff) { // Only load orders for users
            session.setOrders(main.getDatabaseManager().getOrderDAO().getValues()
                    .stream().filter(order -> order.getUsername().equals(session.getUsername()))
                    .collect(Collectors.toCollection(LinkedList::new))); // Setting orders
        }

        setGreetingText(user.getDisplayGreeting()); // Setting greeting label
        setVisible(true); // Showing Menu
    }

    public void logout() {
        main.getUserSession().clear(); // Clearing session
        setGreetingText(null); // Clearing label
        dispose(); // Hiding menu
        main.getMainMenu().setVisible(true); // Show main menu
    }

    public abstract void setGreetingText(String text);
}
