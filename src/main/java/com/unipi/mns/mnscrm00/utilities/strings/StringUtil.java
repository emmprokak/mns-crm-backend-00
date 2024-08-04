package com.unipi.mns.mnscrm00.utilities.strings;

import java.util.Objects;

public class StringUtil {

    public static boolean stringsAreEqual(String s1, String s2){
        return Objects.equals(s1, s2);
    }

    public static boolean stringIsEmptyOrNull(String s){
        return s == null || s.isEmpty();
    }
}
