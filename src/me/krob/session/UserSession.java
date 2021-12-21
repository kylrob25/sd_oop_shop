package me.krob.session;

import me.krob.model.order.Order;
import me.krob.model.user.User;

import java.util.*;

public class UserSession {
    private User user;
    private LinkedList<Order> orders;

    public void clear() {
        user = null;
        orders = null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrders(LinkedList<Order> orders) {
        this.orders = orders;
        orders.sort(Collections.reverseOrder(Comparator.comparing(Order::getDate)));
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
