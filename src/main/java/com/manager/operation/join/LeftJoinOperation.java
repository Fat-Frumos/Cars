package com.manager.operation.join;

import com.manager.operation.JoinOperation;
import com.manager.row.DataRow;
import com.manager.row.JoinedDataRow;

import java.util.*;

public class LeftJoinOperation<K, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {
    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftRow, Collection<DataRow<K, V2>> rightRow) {

        List<JoinedDataRow<K, V1, V2>> joinedRow = new ArrayList<>();
        JoinedDataRow<K, V1, V2> dataRow = null;
        Set<K> keys = new HashSet<>();

        for (DataRow<K, V1> city : leftRow) {
            for (DataRow<K, V2> country : rightRow) {
                if (city.key() == country.key()) {
                    keys.add(city.key());
                    dataRow = new JoinedDataRow<>(city.key(), city.value(), country.value());
                } else if (!keys.contains(city.key())) {
                    dataRow = (new JoinedDataRow<>(city.key(), city.value(), null));
                }
            }
            joinedRow.add(dataRow);
        }
        return joinedRow;
    }
}
