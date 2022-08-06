package com.generic.operation.join;

import com.generic.operation.JoinOperation;
import com.generic.row.DataRow;
import com.generic.row.JoinedDataRow;

import java.util.*;

public class RightJoinOperation<K, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {

    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection, Collection<DataRow<K, V2>> rightCollection) {

        Collection<JoinedDataRow<K, V1, V2>> joinedRow = new ArrayList<>();

        Set<K> keys = new HashSet<>();

        for (DataRow<K, V2> city : rightCollection) {
            for (DataRow<K, V1> country : leftCollection) {
                if (getJoinedDataRow(joinedRow, keys, city, country)) {
                    break;
                }
            }
        }
        return joinedRow;
    }

    private boolean getJoinedDataRow(Collection<JoinedDataRow<K, V1, V2>> joinedRow, Set<K> keys, DataRow<K, V2> city, DataRow<K, V1> country) {
        if (country.key() == city.key()) {
            keys.add(country.key());
            joinedRow.add(new JoinedDataRow<>(country.key(), country.value(), city.value()));
            return true;
        } else if (!keys.contains(country.key())) {
            joinedRow.add(new JoinedDataRow<>(city.key(), null, city.value()));
        }
        return false;
    }
}