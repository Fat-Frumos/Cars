package com.manager;

import java.util.Collection;

public class InnerJoinOperation implements JoinOperation{
    @Override
    public Collection join(Collection leftCollection, Collection rightCollection) {
//        Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection, Collection<DataRow<K, V2>> rightCollection);
        return null;
    }
}
