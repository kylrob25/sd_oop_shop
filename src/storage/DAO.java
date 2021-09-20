package storage;

import java.util.HashMap;
import java.util.Map;

public class DAO<K, V> {
    private final Map<K, V> map = new HashMap<>();

    private final DBManager manager;

    public DAO(DBManager manager) {
        this.manager = manager;
    }

    public void load() {

    }
}
