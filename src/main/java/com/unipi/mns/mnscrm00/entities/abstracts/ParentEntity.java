package com.unipi.mns.mnscrm00.entities.abstracts;

import java.util.List;

public interface ParentEntity {
    <C> List<C> getChildrenEntities(Class<C> childType);
    <C> void addChild(Class<C> childType, C child);
    <C> void removeChild(Class<C> childType, C child);
}
