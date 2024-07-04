package com.unipi.mns.mnscrm00.entities.abstracts;

public interface Sendable<T> {
    T toDTOSimple();
    T toDTOComplete();
    T toDTOMinimal();
}
