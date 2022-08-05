package com.manager;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Builder
@Setter
@Getter
public final class DataRow<K, V> {
    private final K key;
    private final V value;

    public DataRow(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRow<?, ?> dataRow = (DataRow<?, ?>) o;
        return Objects.equals(key, dataRow.key) && Objects.equals(value, dataRow.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    @Override
    public String toString() {
        return "DataRow[" + "key=" + key + ", " + "value=" + value + ']';
    }

}
