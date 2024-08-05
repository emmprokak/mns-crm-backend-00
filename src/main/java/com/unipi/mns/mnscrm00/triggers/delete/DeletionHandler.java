package com.unipi.mns.mnscrm00.triggers.delete;

public interface DeletionHandler<T> {
    public T delete(T entity);
    public T handleParentReferences(T entity);
    public T handleChildReferences(T entity);
}
