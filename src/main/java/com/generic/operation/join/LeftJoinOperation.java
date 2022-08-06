package com.generic.operation.join;

import com.generic.operation.JoinOperation;
import com.generic.row.DataRow;
import com.generic.row.JoinedDataRow;

import java.util.*;

public class LeftJoinOperation<K, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {
    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftRow, Collection<DataRow<K, V2>> rightRow) {

        Set<K> keys = new HashSet<>();
        List<JoinedDataRow<K, V1, V2>> joinedRow = new ArrayList<>();
        for (DataRow<K, V1> city : leftRow) {
            JoinedDataRow<K, V1, V2> dataRow = null;
            for (DataRow<K, V2> country : rightRow) {
                dataRow = getJoinedDataRow(dataRow, keys, city, country);
            }
            joinedRow.add(dataRow);
        }
        return joinedRow;
    }

    private JoinedDataRow<K, V1, V2> getJoinedDataRow(JoinedDataRow<K, V1, V2> dataRow, Set<K> keys, DataRow<K, V1> city, DataRow<K, V2> country) {

        if (city.key() == country.key()) {
            dataRow = new JoinedDataRow<>(city.key(), city.value(), country.value());
            keys.add(city.key());
        } else if (!keys.contains(city.key())) {
            dataRow = new JoinedDataRow<>(city.key(), city.value(), null);
        }
        return dataRow;
    }
}
