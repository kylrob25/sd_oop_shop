package me.krob.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class DAO<K, V> {
    protected final Map<K, V> dataMap = new HashMap<>();

    protected final DatabaseManager manager;

    public DAO(DatabaseManager manager) {
        this.manager = manager;
    }

    public abstract void load();

    public abstract boolean insert(V value);

    public abstract boolean modify(V value, String field, Object obj);

    public V get(K key) {
        return dataMap.get(key);
    }

    public Collection<V> getValues() {
        return dataMap.values();
    }
}
