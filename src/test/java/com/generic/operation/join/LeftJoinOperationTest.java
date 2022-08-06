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

class LeftJoinOperationTest {
    List<JoinedDataRow<Integer, String, String>> expected;
    LeftJoinOperation<Integer, String, String> leftJoin = new LeftJoinOperation<>();

    @BeforeEach
    void setUp() {
        ArrayList<DataRow<Integer, String>> cities = getCities();
        ArrayList<DataRow<Integer, String>> counties = getCounties();
        expected = (List<JoinedDataRow<Integer, String, String>>) leftJoin.join( counties, cities);
    }

    @Test
    void join() {

        String joiner = builder(expected);

        String expected = getExpectedLeft();

        assertEquals(expected, joiner);
    }

        @Test
    void joinInner() {
        assertEquals(leftJoin.join(getCounties(), getCities()), expected);

        String collect = leftJoin.join(getCounties(), getCities()).stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));

        assertEquals(collect, expected.toString());
    }

    @Test
    void joinLeft() {
        LeftJoinOperation<Integer, String, String> leftJoin = new LeftJoinOperation<>();
        assertEquals(leftJoin.join(getCounties(), getCities()), expected);
    }
}