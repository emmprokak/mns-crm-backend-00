package com.unipi.mns.mnscrm00.constants;

import com.unipi.mns.mnscrm00.constants.dto.ConstantDTOs;
import com.unipi.mns.mnscrm00.constants.error.ConstantErrors;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static ConstantErrors CONSTANT_ERRORS;
    public static ConstantDTOs CONSTANT_DTOs;

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



}
