package com.generic.operation.join;

import com.generic.row.JoinedDataRow;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.generic.row.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RightJoinOperationTest {

    RightJoinOperation<Integer, String, String> rightJoin = new RightJoinOperation<>();
    Collection<JoinedDataRow<Integer, String, String>> expected = new ArrayList<>(Arrays.asList(
            (new JoinedDataRow<>(0, "Ukraine", "Kiev")),
            (new JoinedDataRow<>(1, "Germany", "Berlin")),
            (new JoinedDataRow<>(3, null, "Budapest"))
    ));

    @Test
    void testRightJoinOperationToString() {

        assertEquals(rightJoin.join(getCounties(), getCities()).toString(), getExpectedRightJoinString());
    }

    @Test
    public void testRightJoin() {

        assertEquals(rightJoin.join(getCounties(), getCities()), expected);

    }

    @Test
    void testRight() {
        assertEquals(getExpectedRightJoinString(), builder(expected));
    }
}
