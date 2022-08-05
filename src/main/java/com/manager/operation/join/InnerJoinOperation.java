package com.manager.operation.join;

import com.manager.operation.JoinOperation;
import com.manager.row.DataRow;
import com.manager.row.JoinedDataRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InnerJoinOperation<K, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {
    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftRow, Collection<DataRow<K, V2>> rightRow) {

        List<DataRow<K, V1>> cities = (List<DataRow<K, V1>>) leftRow;
        List<DataRow<K, V2>> countries = (List<DataRow<K, V2>>) rightRow;

        List<JoinedDataRow<K, V1, V2>> joinedData = new ArrayList<>();

        cities.forEach(city -> countries.stream()
                .filter(country -> city.key() == country.key())
                .map(country -> new JoinedDataRow<>(city.key(), city.value(), country.value()))
                .forEach(joinedData::add));

        return joinedData;
    }
}
