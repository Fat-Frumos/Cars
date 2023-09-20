package com.generic.row;

public final class JoinedDataRow<K, V1, V2> extends DataRow<K, V1> {
    private final K id;
    private final V2 city;
    private final V1 country;

    public JoinedDataRow(K id, V1 country, V2 city) {
        super(id, country);
        this.id = id;
        this.city = city;
        this.country = country;
    }

    public K key() {
        return id;
    }

    public V2 city() {
        return city;
    }

    public V1 country() {
        return country;
    }

    @Override
    public String toString() {
        return "{ " +
                "\"key\": " + id +
                ", \"country\": \"" + country +
                "\", \"city\": \"" + city +
                "\" }";
    }
}
