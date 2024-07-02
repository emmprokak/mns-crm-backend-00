package com.unipi.mns.mnscrm00.utilities.error;

import com.unipi.mns.mnscrm00.constants.error.ConstantErrors;

public class ErrorMessageUtility {
    private static ConstantErrors  CONSTANT_ERRORS;
    private final static String ENTITY_KEYWORD = CONSTANT_ERRORS.ENTITY_REFERENCE;

    public static String getEntityNotFoundBySpecifier(String entityName, String specifier){
        return CONSTANT_ERRORS.ENTITY_NOT_FOUND.replace(ENTITY_KEYWORD, entityName) + " by " + specifier;
    }

    public static String getListEntityNotFound(String entityName, String specifier){
        return CONSTANT_ERRORS.ENTITY_NOT_FOUND.replace(ENTITY_KEYWORD, entityName) + " by " + specifier;
    }
}
