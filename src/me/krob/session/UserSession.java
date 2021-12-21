package me.krob.session;

import me.krob.model.order.Order;
import me.krob.model.user.User;

import java.util.List;

public class UserSession {
    private User user;
    private List<Order> orders;

    public void clear() {
        user = null;
        orders = null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isActive() {
        return user != null;
    }
}
