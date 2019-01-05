package com.example.javipercas.endprojectifp.Utils;

import java.util.Date;
import java.util.UUID;

public class Utils {

    //Constantes columnas tabla user
    interface columnsUser {
        public static final String USERS_USERNAME = "USERNAME";
        public static final String USERS_PASSWORD = "PASSWORD";
        public static final String USERS_EMAIL = "EMAIL";
    }

    //Constantes columnas tabla user_profile
    interface columnsUserProfile {
        public static final String USERS_PROFILE_ID = "ID";
        public static final String USERS_PROFILE_NAME = "NAME";
        public static final String USERS_PROFILE_SECOND_NAME = "SECOND_NAME";
        public static final String USERS_PROFILE_CITY = "CITY";
        public static final String USERS_PROFILE_PHONE = "PHONE";
        public static final String USERS_PROFILE_USER_EMAIL = "USER_EMAIL";
    }

    //Constantes columnas tabla pets
    interface columnsPets {
        public static final String PETS_NAME = "NAME";
        public static final String PETS_AGE = "AGE";
        public static final String PETS_SEX = "SEX";
        public static final String PETS_STERILIZED = "STERILIZED";
        public static final String PETS_RACE = "RACE";
        public static final String PETS_COLOR = "COLOR";
        public static final String PETS_CHARACTER = "CHARACTER";
        public static final String PETS_USER_EMAIL = "USER_EMAIL";
    }

    //Constantes columnas tabla interes_points
    interface columnsInteresPoints {
        public static final String INTERES_NAME = "NAME";
        public static final String INTERES_DESCRIPTION = "DESCRIPTION";
        public static final String INTERES_ADDRESS = "ADDRESS";
        public static final String INTERES_PHONE = "PHONE";
        public static final String INTERES_USER_EMAIL = "USER_EMAIL";
    }

    public static class Users implements columnsUser {
        public static String generateIdUser() {
            return "U-" + UUID.randomUUID().toString();
        }
    }

    public static class UsersProfile implements columnsUserProfile {
        public static String generarIdUsersProfile() {
            return "UP-" + UUID.randomUUID().toString();
        }
    }

    public static class Pets implements columnsPets {
        public static String generateIdPets() {
            return "P-" + UUID.randomUUID().toString();
        }
    }

    public static class InteresPoints implements columnsInteresPoints {
        public static String generateIdInteresPoints() {
            return "IP-" + UUID.randomUUID().toString();
        }
    }
    /*
    public static final String createTableUser = "CREATE TABLE " + USERS_TABLE + "(\n" +
            USERS_ID + " int(11) NOT NULL,\n" +
            USERS_USERNAME + " USERNAME` varchar(50) COLLATE utf8_spanish_ci NOT NULL,\n" +
            USERS_PASSWORD + " PASSWORD` varchar(50) COLLATE utf8_spanish_ci NOT NULL,\n" +
            USERS_EMAIL + " EMAIL` varchar(255) COLLATE utf8_spanish_ci NOT NULL)";
*/
}
