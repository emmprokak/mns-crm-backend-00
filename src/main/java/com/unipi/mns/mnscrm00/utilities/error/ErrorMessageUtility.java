package com.unipi.mns.mnscrm00.utilities.error;


public class ErrorMessageUtility {

    public static String getEntityNotFoundBySpecifier(String entityName, String specifier){
        return entityName + " not found by " + specifier;
    }

    public static String getListEntityNotFound(String entityName, String specifier){
        return "List of " + entityName + " not found by " + specifier;
    }

    public static String getRelationshipNotFound(String entityNameParent, String entityNameChild){
        return "Couldn't find relationship between " + entityNameParent + " and " + entityNameChild;
    }
}
