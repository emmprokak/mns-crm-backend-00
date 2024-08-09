package com.unipi.mns.mnscrm00.entities.builders;

import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;

public interface EntityBuilder<T extends DataEntity> {
    T build();
}
