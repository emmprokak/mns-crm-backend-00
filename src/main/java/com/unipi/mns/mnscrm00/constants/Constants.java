package com.unipi.mns.mnscrm00.constants;

public class Constants {

    private Constants(){

    }

    public static class DTO {
        public final static int CONVERT_TO_DTO_MINIMAL = 1;
        public final static int CONVERT_TO_DTO_SIMPLE = 2;
        public final static int CONVERT_TO_DTO_COMPLETE = 3;
    }

    public static class Error {
        public final static String ENTITY_REFERENCE = "ENTITY";
        public final static String ENTITY_NOT_FOUND = ENTITY_REFERENCE + " not found";
        public final static String LIST_ENTITY_NOT_FOUND = "Data in list of " + ENTITY_REFERENCE + " not found";
    }

    public static class Entity {
        public final static String ACCOUNT = "Account";
        public final static String CONTACT = "Contact";
        public final static String LEAD = "Lead";
        public final static String OPPORTUNITY = "Opportunity";
        public final static String CASE = "Case";
        public final static String VOICE_CALL = "VoiceCall";
        public final static String TASK = "Task";
        public final static String CONFIG = "Configuration element";
    }

    public static class Specifier {
        public final static String ID = "id";

    }
}
