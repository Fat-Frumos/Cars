package com.manager.row;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataRowTest {

    @Test
    void getKey() {
    }

    @Test
    void getValue() {
        DataRow actual = new DataRow(0, "Kyiv");

        assertEquals(0, actual.key());
        assertEquals("Kyiv", actual.value());
    }

    @Test
    void builder() {
    }
}