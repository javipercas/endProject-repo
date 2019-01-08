package com.example.javipercas.endprojectifp.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.javipercas.endprojectifp.Utils.Utils.*;


public class DataBase extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "endProject.db";

    private static final int ACTUAL_VERSION = 1;

    private final Context contexto;

    interface Tables {
        String USERS_TABLE = "USERS";
        String USERS_PROFILE_TABLE = "USERS_PROFILE";
        String PETS_TABLE = "PETS";
        String INTERES_TABLE = "INTERES_POINTS";
    }

    /*
    interface References {
        String ID_USERS  = String.format("REFERENCES %s(%s) ON DELETE CASCADE", Tables.USERS_TABLE, Users.USERS_ID);
        String ID_USERS_PROFILE = String.format("REFERENCES %s(%s)", Tables.USERS_PROFILE_TABLE, UsersProfile.USERS_ID);
        String ID_PETS = String.format("REFERENCES %s(%s)", Tables.PETS_TABLE, Pets.USERS_ID);
        String ID_INTERES_POINTS = String.format("REFERENCES %s(%s)", Tables.INTERES_TABLE, InteresPoints.USERS_ID);
    }
    */

    public DataBase(Context context) {
        super(context, DATA_BASE_NAME, null, ACTUAL_VERSION);
        this.contexto = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.USERS_TABLE + " (\n" +
                Users.USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                Users.USERS_USERNAME + " TEXT UNIQUE NOT NULL,\n" +
                Users.USERS_PASSWORD + " TEXT NOT NULL,\n" +
                Users.USERS_EMAIL + " TEXT UNIQUE NOT NULL)");

        db.execSQL("CREATE TABLE " + Tables.USERS_PROFILE_TABLE + " (\n" +
                UsersProfile.USERS_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                UsersProfile.USERS_PROFILE_NAME  + " TEXT NOT NULL,\n" +
                UsersProfile.USERS_PROFILE_SECOND_NAME + " TEXT NOT NULL,\n" +
                UsersProfile.USERS_PROFILE_CITY + " TEXT NOT NULL,\n" +
                UsersProfile.USERS_PROFILE_PHONE + " INTEGER NOT NULL,\n" +
                UsersProfile.USERS_USER_ID + " INTEGER UNIQUE NOT NULL," +
                " FOREIGN KEY (" + UsersProfile.USERS_USER_ID + ") REFERENCES " + Tables.USERS_TABLE + "(" + Users.USERS_ID + "));");

        db.execSQL("CREATE TABLE " + Tables.PETS_TABLE + " (\n" +
                Pets.PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                Pets.PETS_NAME + " TEXT NOT NULL,\n" +
                Pets.PETS_AGE+ " INTEGER DEFAULT NULL,\n" +
                Pets.PETS_SEX + " NUMERIC NOT NULL,\n" +
                Pets.PETS_STERILIZED + " NUMERIC DEFAULT NULL,\n" +
                Pets.PETS_RACE + " TEXT NOT NULL,\n" +
                Pets.PETS_COLOR + " TEXT DEFAULT NULL,\n" +
                Pets.PETS_CHARACTER + " TEXT DEFAULT NULL,\n" +
                Pets.PETS_USER_ID + " iINTEGER NOT NULL, \n" +
                " FOREIGN KEY (" + Pets.PETS_USER_ID + ") REFERENCES " + Tables.USERS_TABLE + "(" + Users.USERS_ID + "));");

        db.execSQL("CREATE TABLE `interes_points` (\n" +
                InteresPoints.INTERES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                InteresPoints.INTERES_NAME + " TEXT NOT NULL,\n" +
                InteresPoints.INTERES_DESCRIPTION + " TEXT DEFAULT NULL,\n" +
                InteresPoints.INTERES_ADDRESS + " TEXT NOT NULL,\n" +
                InteresPoints.INTERES_PHONE + " INTEGER DEFAULT NULL, \n" +
                InteresPoints.INTERES_USER_ID + " INTEGER NOT NULL, \n" +
                " FOREIGN KEY (" + InteresPoints.INTERES_USER_ID + ") REFERENCES " + Tables.USERS_TABLE + "(" + Users.USERS_EMAIL + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.USERS_PROFILE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.PETS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.INTERES_TABLE);
    }



}

