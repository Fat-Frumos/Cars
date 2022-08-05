package com.manager;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Builder
@Setter
@Getter
public final class JoinedDataRow<K, V1, V2> {
    private final K key;
    private final V1 left;
    private final V2 right;

    public JoinedDataRow(K key, V1 left, V2 right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinedDataRow<?, ?, ?> that = (JoinedDataRow<?, ?, ?>) o;
        return Objects.equals(key, that.key) && Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, left, right);
    }

    public K key() {
        return key;
    }

    public V1 left() {
        return left;
    }

    public V2 right() {
        return right;
    }

    @Override
    public String toString() {
        return "JoinedDataRow[" +
                "key=" + key + ", " +
                "left=" + left + ", " +
                "right=" + right + ']';
    }

}
