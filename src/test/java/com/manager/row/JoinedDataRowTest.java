package com.manager.row;

import com.manager.operation.join.InnerJoinOperation;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedHashSet;

import static com.manager.row.Util.getCities;
import static com.manager.row.Util.getCounties;

class JoinedDataRowTest {
    LinkedHashSet<DataRow<Integer, String>> countries = getCounties();
    LinkedHashSet<DataRow<Integer, String>> cities = getCities();

    @Test
    void mapper() {

        InnerJoinOperation collection = new InnerJoinOperation();
        Collection join = collection.join(cities, countries);
        for (Object o : join) {
            System.out.println(o);
        }
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
//        System.out.println(getString());
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
}