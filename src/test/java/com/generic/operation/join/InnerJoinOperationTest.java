package com.generic.operation.join;

import com.generic.row.DataRow;
import com.generic.row.JoinedDataRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.generic.row.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InnerJoinOperationTest {
    List<JoinedDataRow<Integer, String, String>> expected;
    InnerJoinOperation<Integer, String, String> innerJoin = new InnerJoinOperation<>();
    ArrayList<DataRow<Integer, String>> cities = getCities();
    ArrayList<DataRow<Integer, String>> counties = getCounties();

    @BeforeEach
    void setUp() {
        expected = (List<JoinedDataRow<Integer, String, String>>) innerJoin.join(counties, cities);
    }

    @Test
    void join() {

        String actual = "[{ \"key\": 0, \"country\": \"Ukraine\", \"city\": \"Kiev\" }, " +
                "{ \"key\": 1, \"country\": \"Germany\", \"city\": \"Berlin\" }]";

        assertEquals(actual, builder(expected));
    }

    @Test
    void joinInner() {

        assertEquals(innerJoin.join(counties, cities), expected);

        String actual = innerJoin.join(getCounties(), getCities()).stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(", ", "[", "]"));

        assertEquals(actual, expected.toString());
    }

    @Test
    public void testOperation() {
        assertEquals(innerJoin.join(getCounties(), getCities()), getInnerRowsToString());
    }
}