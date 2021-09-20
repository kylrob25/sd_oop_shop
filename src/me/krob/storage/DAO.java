package me.krob.storage;

import me.krob.model.user.users.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class DAO<K, V> {
    protected final Map<K, V> map = new HashMap<>();

    protected final DBManager manager;
    protected final String tableName;

    public DAO(DBManager manager, String tableName) {
        this.manager = manager;
        this.tableName = tableName;
    }

    public abstract void load();
}
