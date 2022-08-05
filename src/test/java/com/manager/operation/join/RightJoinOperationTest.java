package com.manager.operation.join;

import com.manager.row.DataRow;
import com.manager.row.JoinedDataRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.manager.row.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RightJoinOperationTest {

    List<JoinedDataRow<Integer, String, String>> dataRows;

    @BeforeEach
    void setUp() {
        Set<DataRow<Integer, String>> cities = getCities();
        Set<DataRow<Integer, String>> counties = getCounties();

        RightJoinOperation<Integer, String, String> rightJoin = new RightJoinOperation<>();
        dataRows = (List<JoinedDataRow<Integer, String, String>>) rightJoin.join(counties, cities);
    }

    @Test
    void join() {
        String joiner = builder(dataRows);
        assertEquals(getExpectedRight(), joiner);
    }
}