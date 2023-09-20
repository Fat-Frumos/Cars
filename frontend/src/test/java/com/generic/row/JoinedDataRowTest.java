package com.generic.row;

import com.generic.operation.join.InnerJoinOperation;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.ArrayList;

import static com.generic.row.Util.getCounties;
import static com.generic.row.Util.getCities;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JoinedDataRowTest {

    JoinedDataRow<Integer, String, String> ua = new JoinedDataRow<>(0, "Ukraine", "Kiev");

    @Test
    void mapper() {

        InnerJoinOperation collection = new InnerJoinOperation();
        Collection join = collection.join(getCounties(), getCities());
        for (Object o : join) {
            System.out.println(o);
        }
    }

    private static String getString(JoinedDataRow<Integer, String, String> dataRow) {
        return dataRow.key() + "" + dataRow.city() + "" + dataRow.country();
    }

    @Test
    void key() {
        assertEquals(0, ua.key());
    }

    @Test
    void city() {
        assertEquals("Kiev", ua.city());
    }

    @Test
    void country() {
        assertEquals("Ukraine", ua.country());
    }
}