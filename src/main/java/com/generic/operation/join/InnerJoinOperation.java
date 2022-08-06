package com.generic.operation.join;

import com.generic.operation.JoinOperation;
import com.generic.row.DataRow;
import com.generic.row.JoinedDataRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InnerJoinOperation<K, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {
    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection, Collection<DataRow<K, V2>> rightCollection) {

        List<JoinedDataRow<K, V1, V2>> joinedRow = new ArrayList<>();

        leftCollection.forEach(city -> rightCollection.stream()
                .filter(country -> city.key() == country.key())
                .map(country -> joinRow(city, country))
                .forEach(joinedRow::add));
        return joinedRow;
    }

    private JoinedDataRow<K, V1, V2> joinRow(DataRow<K, V1> country, DataRow<K, V2> city) {
        return new JoinedDataRow<>(city.key(), country.value(), city.value());
    }
}
