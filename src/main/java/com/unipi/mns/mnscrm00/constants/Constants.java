package com.unipi.mns.mnscrm00.constants;

import com.unipi.mns.mnscrm00.constants.error.ConstantErrors;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static ConstantErrors CONSTANT_ERRORS;

    public enum Entity{
        ACCOUNT("Account"),
        CONTACT("Contact"),
        LEAD("Lead");

        private final String description;
        private static final Map<Entity, String> descriptions = new HashMap<>();

        static {
            for (Entity type : Entity.values()) {
                descriptions.put(type, type.description);
            }
        }

        Entity(String description) {
            this.description = description;
        }

        public static String getDescription(Entity type) {
            return descriptions.get(type);
        }
    }

    public enum Specifier{
        ID("id");

        private final String message;

        Specifier(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }


}
