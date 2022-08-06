package com.generic.row;

import java.util.Objects;

public class DataRow<K, V> {
    private final K id;
    private final V name;

    public DataRow(K id, V name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public K key() {
        return id;
    }

    public V value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRow<?, ?> dataRow = (DataRow<?, ?>) o;
        return Objects.equals(id, dataRow.id) && Objects.equals(name, dataRow.name);
    }

    @Override
    public String toString() {
        return "{ " +
                "\"key\": \"" + id +
                "\", \"value\": \"" + name +
                "\" }";
    }
}
