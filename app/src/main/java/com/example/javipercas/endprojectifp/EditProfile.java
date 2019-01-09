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

public class EditProfile extends AppCompatActivity {

    EditText etEditUsername, etEditName, etEditSecondName, etEditCity, etEditEmail, etEditPhone;
    Button btEditUpdate;
    SQLiteDatabase db;
    String idUser;
    int idUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUserLogin");
        idUserLogin = Integer.parseInt(idUser);

        etEditUsername = (EditText)findViewById(R.id.etEditUser);
        etEditName = (EditText)findViewById(R.id.etEditName);
        etEditSecondName = (EditText)findViewById(R.id.etEditSecondName);
        etEditEmail = (EditText)findViewById(R.id.etSEditEmail);
        etEditCity = (EditText)findViewById(R.id.etEditPoblacion);
        etEditPhone = (EditText)findViewById(R.id.etEditPhone);
        btEditUpdate = (Button)findViewById(R.id.btEditUpdate);

        etEditEmail.setFocusable(false);
        etEditUsername.setFocusable(false);

        try {
            Cursor cursor = db.rawQuery("SELECT u.USERNAME, up.NAME, up.SECOND_NAME, up.CITY, u.EMAIL, up.PHONE, u.ID " +
                    "FROM USERS AS u INNER JOIN USERS_PROFILE AS up ON u.ID = up.USER_ID WHERE u.ID = '" +
                    idUserLogin + "'", null);

            if (cursor.moveToFirst() == true) {
                String cursorUsername = cursor.getString(0).toLowerCase();
                String cursorName = cursor.getString(1);
                String cursorSecondName = cursor.getString(2);
                String cursorCity = cursor.getString(3);
                String cursorEmail = cursor.getString(4);
                String cursorPhone = cursor.getString(5);
                int cursorUserId = Integer.parseInt(cursor.getString(6));

                if (idUserLogin == cursorUserId) {
                    etEditUsername.setText(cursorUsername);
                    etEditName.setText(cursorName);
                    etEditSecondName.setText(cursorSecondName);
                    etEditCity.setText(cursorCity);
                    etEditEmail.setText(cursorEmail);
                    etEditPhone.setText(cursorPhone);
                }
            }
        } catch (Exception e) {
            Toast.makeText(EditProfile.this, "No se han encontrado los datos del perfil", Toast.LENGTH_SHORT).show();

        }

        btEditUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUsersProfile(etEditUsername.getText().toString(),
                                    etEditName.getText().toString(),
                                    etEditSecondName.getText().toString(),
                                    etEditCity.getText().toString(),
                                    etEditPhone.getText().toString(),
                                    idUserLogin,
                                    etEditEmail.getText().toString()
                                    );
            }
        });

    }

    private void insertUsersProfile(String username, String name, String secondName, String city, String phone, int userId, String email) {
        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT ID, USERNAME, EMAIL FROM USERS  WHERE ID = " + idUserLogin + " AND USERNAME = '" +
                username + "' AND EMAIL = '" + email + "'", null);

        if (cursor.moveToFirst() == true) {

            db.execSQL("UPDATE USERS_PROFILE " +
                    "SET NAME = '" + name + "', " +
                    "SECOND_NAME = '" + secondName + "', " +
                    "CITY = '" + city + "', " +
                    "PHONE = '" + phone + "' WHERE USER_ID = " + userId);

            Intent showProfile = new Intent(EditProfile.this, ShowProfile.class);
            showProfile.putExtra("idUserLogin", idUserLogin + "");
            startActivity(showProfile);

        } else {
            Toast.makeText(EditProfile.this, "No se han podido actualizar los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
