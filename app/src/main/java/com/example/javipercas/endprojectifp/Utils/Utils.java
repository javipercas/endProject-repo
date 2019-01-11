package com.example.javipercas.endprojectifp.Utils;

import java.util.Date;
import java.util.UUID;

public class Utils {

    //Constantes columnas tabla user
    interface columnsUser {
        public static final String USERS_ID = "ID";
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
        public static final String USERS_USER_ID = "USER_ID";
    }

    //Constantes columnas tabla pets
    interface columnsPets {
        public static final String PETS_ID = "ID";
        public static final String PETS_NAME = "NAME";
        public static final String PETS_AGE = "AGE";
        public static final String PETS_SEX = "SEX";
        public static final String PETS_STERILIZED = "STERILIZED";
        public static final String PETS_RACE = "RACE";
        public static final String PETS_COLOR = "COLOR";
        public static final String PETS_CHARACTER = "CHARACTER";
        public static final String PETS_USER_ID = "USER_ID";
    }

    //Constantes columnas tabla interes_points
    interface columnsInteresPoints {
        public static final String INTERES_ID = "ID";
        public static final String INTERES_NAME = "NAME";
        public static final String INTERES_DESCRIPTION = "DESCRIPTION";
        public static final String INTERES_ADDRESS = "ADDRESS";
        public static final String INTERES_PHONE = "PHONE";
        public static final String INTERES_USER_ID = "USER_ID";
    }

    //Constantes columnas tabla Posts
    interface columnsPosts {
        public static final String POSTS_ID = "_id";
        public static final String POSTS_TITLE = "TITLE";
        public static final String POSTS_DESCRIPTION = "DESCRIPTION";
        public static final String POSTS_CREATE_DATE = "CREATE_DATE";
        public static final String POSTS_VISIBLE = "VISIBLE";
        public static final String POSTS_TYPE = "TYPE";
        public static final String POSTS_USER_ID = "USER_ID";
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

    public static class Posts implements columnsPosts {
        public static String generateIdPosts() {
            return "PO-" + UUID.randomUUID().toString();
        }
    }
}
