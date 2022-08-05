package com.manager.row;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringJoiner;

public class Util {
    public static LinkedHashSet<DataRow<Integer, String>> getCities() {
        LinkedHashSet<DataRow<Integer, String>> cities = new LinkedHashSet<>();
        cities.add(new DataRow<>(0, "Ukraine"));
        cities.add(new DataRow<>(1, "Germany"));
        cities.add(new DataRow<>(2, "France"));
        return cities;
    }
    public static LinkedHashSet<DataRow<Integer, String>> getCounties() {
        LinkedHashSet<DataRow<Integer, String>> cities = new LinkedHashSet<>();
        cities.add(new DataRow<>(0, "Kiev"));
        cities.add(new DataRow<>(1, "Berlin"));
        cities.add(new DataRow<>(3, "Budapest"));
        return cities;
    }

    public static String builder(List<JoinedDataRow<Integer, String, String>> dataRows) {
        String string = "";

        for (JoinedDataRow<Integer, String, String> dataRow : dataRows) {
            string += dataRow.toString();
        }

        String joiner = new StringJoiner(",\n", "[", "]")
                .add(string.replace("}{", "},\n{"))
                .toString();

        System.out.println(joiner);
        return joiner;
    }

    public static String getExpectedLeft() {
        String expected = "[{ \"key\": 0, \"city\": \"Kiev\", \"country\": \"Ukraine\" },\n" +
                "{ \"key\": 1, \"city\": \"Berlin\", \"country\": \"Germany\" },\n" +
                "{ \"key\": 3, \"city\": \"Budapest\", \"country\": \"null\" }]";
        return expected;
    }
    public static String getExpectedRight() {
        String expected = "[{ \"key\": 0, \"city\": \"Kiev\", \"country\": \"Ukraine\" },\n" +
                "{ \"key\": 1, \"city\": \"Berlin\", \"country\": \"Germany\" },\n" +
                "{ \"key\": 3, \"city\": \"null\", \"country\": \"France\" }]";
        return expected;
    }
}
