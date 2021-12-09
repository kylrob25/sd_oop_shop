package me.krob.session;

import me.krob.model.user.User;

public class UserSession {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isActive() {
        return user != null;
    }
}
