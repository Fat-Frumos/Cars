package com.generic.row;

import java.util.*;

public class Util {
    public static ArrayList<DataRow<Integer, String>> getCounties() {
        ArrayList<DataRow<Integer, String>> country = new ArrayList<>();
        country.add(new DataRow<>(0, "Ukraine"));
        country.add(new DataRow<>(1, "Germany"));
        country.add(new DataRow<>(2, "France"));
        return country;
    }

    public static ArrayList<DataRow<Integer, String>> getCities() {
        ArrayList<DataRow<Integer, String>> cities = new ArrayList<>();
        cities.add(new DataRow<>(0, "Kiev"));
        cities.add(new DataRow<>(1, "Berlin"));
        cities.add(new DataRow<>(3, "Budapest"));
        return cities;
    }

    public static Collection<JoinedDataRow<Integer, String, String>> getInnerRowsToString() {
        return new LinkedList<>((Arrays.asList(
                new JoinedDataRow<>(0, "Ukraine", "Kiev"),
                new JoinedDataRow<>(1, "Germany", "Berlin")
        )));
    }

    public static String getExpectedLeft() {
        return "[{ \"key\": 0, \"country\": \"Ukraine\", \"city\": \"Kiev\" }, " +
                "{ \"key\": 1, \"country\": \"Germany\", \"city\": \"Berlin\" }, " +
                "{ \"key\": 2, \"country\": \"France\", \"city\": \"null\" }]";
    }

    public static String getExpectedRightJoinString() {
        return "[{ \"key\": 0, \"country\": \"Ukraine\", \"city\": \"Kiev\" }, " +
                "{ \"key\": 1, \"country\": \"Germany\", \"city\": \"Berlin\" }, " +
                "{ \"key\": 3, \"country\": \"null\", \"city\": \"Budapest\" }]";
    }

    public static String builder(Collection<JoinedDataRow<Integer, String, String>> dataRows) {
        StringBuilder string = new StringBuilder();

        for (JoinedDataRow<Integer, String, String> dataRow : dataRows) {
            string.append(dataRow.toString());
        }

        String joiner = new StringJoiner(", ", "[", "]")
                .add(string.toString().replace("}{", "}, {"))
                .toString();

        System.out.println(joiner);
        return joiner;
    }
}