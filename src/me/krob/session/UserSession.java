package me.krob.session;

import me.krob.model.order.Order;
import me.krob.model.user.User;

import java.util.*;

public class UserSession {
    private final LinkedList<Order> orders = new LinkedList<>();

    private User user;

    public void clear() {
        user = null;
        orders.clear();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrders(LinkedList<Order> orders) {
        orders.forEach(this.orders::addFirst);
    }

    public LinkedList<Order> getOrders() {
        return orders;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isActive() {
        return user != null;
    }
}
