package me.krob.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class DAO<K, V> {
    protected final Map<K, V> dataMap = new HashMap<>();

    protected final DatabaseManager manager;
    protected final String tableName;

    public DAO(DatabaseManager manager, String tableName) {
        this.manager = manager;
        this.tableName = tableName;
    }

    public abstract void load();

    public V get(K key) {
        return dataMap.get(key);
    }

    public Collection<V> getValues() {
        return dataMap.values();
    }
}
