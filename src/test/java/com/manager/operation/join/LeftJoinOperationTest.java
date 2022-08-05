package com.manager.operation.join;

import com.manager.row.DataRow;
import com.manager.row.JoinedDataRow;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

class LeftJoinOperationTest {
    Set<DataRow<Integer, String>> cities = new LinkedHashSet<>();
    Set<DataRow<Integer, String>> counties = new LinkedHashSet<>();


    @Test
    void join() {
        cities.add(new DataRow<>(0, "Ukraine"));
        cities.add(new DataRow<>(1, "Germany"));
        cities.add(new DataRow<>(2, "France"));

        counties.add(new DataRow<>(0, "Kiev"));
        counties.add(new DataRow<>(1, "Berlin"));
        counties.add(new DataRow<>(3, "Budapest"));

        LeftJoinOperation<Integer, String, String> leftJoin = new LeftJoinOperation<>();
        ArrayList<JoinedDataRow<Integer, String, String>> dataRows =
                (ArrayList<JoinedDataRow<Integer, String, String>>) leftJoin.join(cities, counties);
        dataRows.forEach(System.out::println);
    }

}