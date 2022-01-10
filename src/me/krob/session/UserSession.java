package me.krob.session;

import me.krob.model.order.Order;
import me.krob.model.user.User;

import java.util.*;

public class UserSession {
    private final LinkedList<Order> orders = new LinkedList<>();

    private User user;
    private boolean staff;

    public void clear() {
        user = null;
        staff = false;
        orders.clear();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
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

    public boolean isStaff() {
        return staff;
    }
}
