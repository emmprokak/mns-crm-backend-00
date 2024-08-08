package com.unipi.mns.mnscrm00.entities.abstracts;

public interface ChildEntity {
    <P> String getParentId(Class<P> entityType);
    <P> void setParentId(Class<P> entityType, P parent);

    <P> P getParent(Class<P> entityType);
    <P> void setParent(Class<P> entityType, P parent);
}
