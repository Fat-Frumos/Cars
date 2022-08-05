package com.manager.operation.join;

import com.manager.row.DataRow;
import com.manager.row.JoinedDataRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static com.manager.row.Util.*;
import static com.manager.row.Util.getExpectedRight;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LeftJoinOperationTest {
    List<JoinedDataRow<Integer, String, String>> dataRows;

    @BeforeEach
    void setUp() {

        LinkedHashSet<DataRow<Integer, String>> counties = getCities();
        LinkedHashSet<DataRow<Integer, String>> cities = getCounties();

        LeftJoinOperation<Integer, String, String> leftJoin = new LeftJoinOperation<>();
        dataRows = (List<JoinedDataRow<Integer, String, String>>) leftJoin.join(cities, counties);
    }

    @Test
    void join() {

        String joiner = builder(dataRows);

        String expectedRight = getExpectedLeft();

        assertEquals(expectedRight, joiner);
    }
    @Test
    void joinL() {

        LeftJoinOperation<Integer, String, String> leftJoin = new LeftJoinOperation<>();
        ArrayList<JoinedDataRow<Integer, String, String>> leftJoinResult =
                (ArrayList<JoinedDataRow<Integer, String, String>>) leftJoin.join(getCities(), getCounties());
        leftJoinResult.forEach(System.out::println);
    }
}