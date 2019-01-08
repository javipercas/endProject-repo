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

public class ChangePassword extends AppCompatActivity {

    private EditText etChangeUsername, etChangeOldPass, etChangeNewPass, etChangeRepeatNewPass;
    private Button btChangePass;
    SQLiteDatabase db;
    private String idUser;
    private int idUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUserLogin");
        idUserLogin = Integer.parseInt(idUser);

        etChangeUsername = (EditText)findViewById(R.id.etChangeUsername);
        etChangeOldPass = (EditText)findViewById(R.id.etChangeActualPassword);
        etChangeNewPass = (EditText)findViewById(R.id.etChangeNewPassword);
        etChangeRepeatNewPass = (EditText)findViewById(R.id.etChangeRepeatNewPassword);
        btChangePass = (Button)findViewById(R.id.btChangePassword);

        etChangeUsername.setFocusable(false);

        try {
            Cursor cursor = db.rawQuery("SELECT USERNAME FROM USERS  WHERE ID = '" + idUserLogin + "'", null);

            if (cursor.moveToFirst() == true) {
                String cursorUsername = cursor.getString(0).toLowerCase();

                etChangeUsername.setText(cursorUsername);
            }
        } catch (Exception e) {
            Toast.makeText(ChangePassword.this, "No se han encontrado los datos del perfil", Toast.LENGTH_SHORT).show();
        }

        btChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        try {
            Cursor cursor = db.rawQuery("SELECT USERNAME, PASSWORD, ID FROM USERS  WHERE ID = '" + idUserLogin + "'", null);

            if (cursor.moveToFirst() == true) {
                String cursorUsername = cursor.getString(0).toLowerCase();
                final String cursorPassword = cursor.getString(1);
                final int cursorUserId = Integer.parseInt(cursor.getString(2));

                etChangeUsername.setText(cursorUsername);

                if (idUserLogin == cursorUserId && etChangeOldPass.getText().toString().equals(cursorPassword) &&
                        etChangeNewPass.getText().toString().equals(etChangeRepeatNewPass.getText().toString())) {

                    db.execSQL("UPDATE USERS SET PASSWORD = '" + etChangeNewPass.getText().toString() + "' WHERE ID = " + cursorUserId);

                    Intent showProfile = new Intent(ChangePassword.this, ShowProfile.class);
                    showProfile.putExtra("idUserLogin", idUserLogin + "");
                    startActivity(showProfile);

                } else if (!etChangeOldPass.equals(cursorPassword)) {
                    Toast.makeText(ChangePassword.this, "Las contraseña introducida no coincide con la del usuario", Toast.LENGTH_SHORT).show();
                } else if (etChangeNewPass.equals(etChangeRepeatNewPass)) {
                    Toast.makeText(ChangePassword.this, "Las nuevas contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (Exception e) {
            Toast.makeText(ChangePassword.this, "No se han encontrado los datos del perfil", Toast.LENGTH_SHORT).show();
        }
    }
}
