package com.manager.operation.join;

import com.manager.row.DataRow;
import com.manager.row.JoinedDataRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;

import static com.manager.row.Util.*;
import static org.junit.jupiter.api.Assertions.*;

class InnerJoinOperationTest {
    List<JoinedDataRow<Integer, String, String>> dataRows;

    @BeforeEach
    void setUp() {

        LinkedHashSet<DataRow<Integer, String>> cities = getCities();
        LinkedHashSet<DataRow<Integer, String>> counties = getCounties();

        InnerJoinOperation<Integer, String, String> innerJoin = new InnerJoinOperation<>();
        dataRows = (List<JoinedDataRow<Integer, String, String>>) innerJoin.join(counties, cities);
    }

    @Test
    void join() {

        String joiner = builder(dataRows);

        String expectedRight = "[{ \"key\": 0, \"city\": \"Kiev\", \"country\": \"Ukraine\" },\n" +
                "{ \"key\": 1, \"city\": \"Berlin\", \"country\": \"Germany\" }]";

        assertEquals(expectedRight, joiner);
    }
}