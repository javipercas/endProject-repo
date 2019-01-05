package com.example.javipercas.endprojectifp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javipercas.endprojectifp.Utils.DataBase;
import com.example.javipercas.endprojectifp.Utils.Utils;

public class SignIn extends AppCompatActivity {

    private EditText etUsername, etPassword, etRepeatPassword, etName,
                    etSecondName, etCity, etEmail, etPhone;
    private Button etRegister;
    private Cursor query;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etUsername = (EditText)findViewById(R.id.etSignInUser);
        etPassword = (EditText)findViewById(R.id.etSignInPassword);
        etRepeatPassword = (EditText)findViewById(R.id.etSignInRepeatPassword);
        etName = (EditText)findViewById(R.id.etSignInName);
        etSecondName = (EditText)findViewById(R.id.etSignInSecondName);
        etCity = (EditText)findViewById(R.id.etSignInPoblacion);
        etEmail = (EditText)findViewById(R.id.etSignInEmail);
        etPhone = (EditText)findViewById(R.id.etSignInPhone);
        etRegister = (Button)findViewById(R.id.idSave);

        etRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().toLowerCase();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String repeatPassword = etRepeatPassword.getText().toString();
                String name = etName.getText().toString();
                String secondName = etSecondName.getText().toString();
                String city = etCity.getText().toString();
                String phone = etPhone.getText().toString();

                if (password.equals(repeatPassword)) {

                    try {
                        insertUsers(username, password, email);

                        boolean createUser = true;

                        if (createUser) {
                            insertUsersProfile(name, secondName, city, phone, email);
                        }

                        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainActivity);

                    }catch (Exception e) {
                        Toast.makeText(SignIn.this, "Has de insertar los datos obligatorios", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(SignIn.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean insertUsers(String username, String password, String email) {
        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.Users.USERS_USERNAME, username);
        contentValues.put(Utils.Users.USERS_PASSWORD, password);
        contentValues.put(Utils.Users.USERS_EMAIL, email);

        long result = db.insert("USERS", null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    private boolean insertUsersProfile(String name, String secondName, String city, String phone, String email) {
        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int numPhone = Integer.parseInt(phone);
        contentValues.put(Utils.UsersProfile.USERS_PROFILE_NAME, name);
        contentValues.put(Utils.UsersProfile.USERS_PROFILE_SECOND_NAME, secondName);
        contentValues.put(Utils.UsersProfile.USERS_PROFILE_CITY, city);
        contentValues.put(Utils.UsersProfile.USERS_PROFILE_PHONE, numPhone);
        contentValues.put(Utils.UsersProfile.USERS_PROFILE_USER_EMAIL, email);

        long result = db.insert("USERS_PROFILE", null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
