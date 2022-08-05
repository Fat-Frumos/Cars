package com.manager.row;

import java.util.Objects;

public class DataRow<K, V> {
    private final K id;
    private final V city;

    public DataRow(K id, V city) {
        this.id = id;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRow<?, ?> dataRow = (DataRow<?, ?>) o;
        return Objects.equals(id, dataRow.id) && Objects.equals(city, dataRow.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city);
    }

    public K key() {
        return id;
    }

    public V value() {
        return city;
    }

    @Override
    public String toString() {
        return "DataRow[" + "key=" + id + ", " + "value=" + city + ']';
    }

}
