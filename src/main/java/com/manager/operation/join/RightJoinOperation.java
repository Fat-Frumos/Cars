
package com.manager.operation.join;

import com.manager.operation.JoinOperation;
import com.manager.row.DataRow;
import com.manager.row.JoinedDataRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class RightJoinOperation<K, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {

    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftRow, Collection<DataRow<K, V2>> rightRow) {

        List<DataRow<K, V1>> cities = (List<DataRow<K, V1>>) leftRow;
        List<DataRow<K, V2>> countries = (List<DataRow<K, V2>>) rightRow;

        List<JoinedDataRow<K, V1, V2>> joinedData = new ArrayList<>();

        countries.forEach(countryMap -> cities.stream()
                .filter(cityMap -> Objects.equals(cityMap.key(), countryMap.key()))
                .map(cityMap -> new JoinedDataRow<>(cityMap.key(), cityMap.value(), countryMap.value()))
                .forEach(joinedData::add));

        return joinedData;
    }
}
