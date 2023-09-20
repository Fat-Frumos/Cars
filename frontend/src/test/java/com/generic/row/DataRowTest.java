package com.generic.row;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataRowTest {
    DataRow<Integer ,String> kiev = new DataRow(0, "Kiev");
    DataRow<Integer ,String> berlin = new DataRow(1, "Berlin");

    @Test
    void getKey() {
        assertEquals(0, kiev.key());
    }

    @Test
    void getValue() {
        assertEquals("Kiev", kiev.value());
    }

    @Test
    void testHashCode() {
        assertEquals(2339440, kiev.hashCode());
        assertEquals(1986303906, berlin.hashCode());
    }

    @Test
    void testEquals() {
        assertFalse(kiev.equals(berlin));
        assertTrue(kiev.equals(new DataRow(0, "Kiev")));
    }

    @Test
    void testToString() {
        assertEquals("{ \"key\": \"0\", \"value\": \"Kiev\" }", kiev.toString());
    }
}