package com.manager.row;

public final class JoinedDataRow<K, V1, V2> extends DataRow<K, V1> {
    private final K key;
    private final V1 city;
    private final V2 country;

    public JoinedDataRow(K key, V1 city, V2 country) {
        super(key, city);
        this.key = key;
        this.city = city;
        this.country = country;
    }

    public K key() {
        return key;
    }

    public V1 city() {
        return city;
    }

    public V2 country() {
        return country;
    }

    @Override
    public String toString() {
        return "{ " +
                "\"key\": " + key +
                ", \"city\": \"" + city +
                "\", \"country\": \"" + country +
                "\" }";
    }
}
