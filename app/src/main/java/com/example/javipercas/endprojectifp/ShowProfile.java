package com.example.javipercas.endprojectifp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javipercas.endprojectifp.Utils.DataBase;

public class ShowProfile extends AppCompatActivity {

    EditText etUsername, etName, etSeccondName, etCity, etEmail, etPhone;
    SQLiteDatabase db;
    String idUser;
    int idUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUserLogin");
        idUserLogin = Integer.parseInt(idUser);

        etUsername = (EditText)findViewById(R.id.etShowUser);
        etName = (EditText)findViewById(R.id.etShowName);
        etSeccondName = (EditText)findViewById(R.id.etShowSecondName);
        etEmail = (EditText)findViewById(R.id.etShowEmail);
        etCity = (EditText)findViewById(R.id.etShowPoblacion);
        etPhone = (EditText)findViewById(R.id.etShowPhone);

        etUsername.setFocusable(false);
        etName.setFocusable(false);
        etSeccondName.setFocusable(false);
        etCity.setFocusable(false);
        etEmail.setFocusable(false);
        etPhone.setFocusable(false);

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
                    etUsername.setText(cursorUsername);
                    etName.setText(cursorName);
                    etSeccondName.setText(cursorSecondName);
                    etCity.setText(cursorCity);
                    etEmail.setText(cursorEmail);
                    etPhone.setText(cursorPhone);
                }
            }
        } catch (Exception e) {
            Toast.makeText(ShowProfile.this, "No se han encontrado los datos del perfil", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btChangePassword:
                Intent changePassword = new Intent(getApplicationContext(), ChangePassword.class);
                changePassword.putExtra("idUserLogin", idUserLogin + "");
                startActivity(changePassword);
                return true;
            case R.id.btEditProfile:
                Intent editProfile = new Intent(getApplicationContext(), EditProfile.class);
                editProfile.putExtra("idUserLogin", idUserLogin + "");
                startActivity(editProfile);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
