package com.manager.row;

import com.manager.operation.join.InnerJoinOperation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class JoinedDataRowTest {

    List<DataRow> countries = new ArrayList<>();
    List<DataRow> cities = new ArrayList<>();

    @Test
    void setUp() {

        cities.add(new DataRow<>(0, "Kiev"));
        cities.add(new DataRow<>(1, "Berlin"));
        cities.add(new DataRow<>(2, "Paris"));

        countries.add(new DataRow<>(0, "Ukraine"));
        countries.add(new DataRow<>(1, "Germany"));
        countries.add(new DataRow<>(3, "France"));

    }

    @Test
    void mapper() {

        InnerJoinOperation collection = new InnerJoinOperation();
        collection.join(cities, countries);
        collection.toString();
    }

    private static String getString(JoinedDataRow<Integer, String, String> dataRow) {
        return dataRow.key() + "" + dataRow.city() + "" + dataRow.country();
    }

    @Test
    void key() {
    }

    @Test
    void left() {
    }

    @Test
    void right() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getKey() {
    }

    @Test
    void getLeft() {
    }

    @Test
    void getRight() {
    }

    @Test
    void builder() {
    }
}