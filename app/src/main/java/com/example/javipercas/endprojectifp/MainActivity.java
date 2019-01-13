package com.example.javipercas.endprojectifp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javipercas.endprojectifp.Utils.DataBase;
import com.example.javipercas.endprojectifp.Utils.Utils;

public class MainActivity extends AppCompatActivity {

    private Button btSignIn, btLogin;
    private EditText etUsername, etPassword;
    private TextView tvRecover;
    private Cursor query;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();

        btSignIn = (Button) findViewById(R.id.btSignIn);
        btLogin = (Button) findViewById(R.id.btLogin);
        etPassword = (EditText) findViewById(R.id.etMainPassword);
        etUsername = (EditText) findViewById(R.id.etMainUsername);
        tvRecover = (TextView)findViewById(R.id.tvRecover);

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(getApplicationContext(), SignIn.class);
                startActivity(signIn);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username != "" && password != "") {
                    Cursor cursor = db.rawQuery("SELECT USERNAME,PASSWORD " +
                            "FROM USERS WHERE USERNAME = '" + username + "' AND PASSWORD = '" +
                            password + "'", null);

                    if (cursor.moveToFirst() == true) {
                        String user = cursor.getString(0).toLowerCase();
                        String pass = cursor.getString(1);

                        int loginId = findByUsername(username);

                        if (username.toLowerCase().equalsIgnoreCase(user) && password.equals(pass) && loginId != -1) {
                            Intent helpLost = new Intent(MainActivity.this, HelpLost.class);
                            helpLost.putExtra("idUserLogin", loginId + "");
                            startActivity(helpLost);

                            etUsername.setText("");
                            etPassword.setText("");
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        etPassword.setText("");

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Introduce usuario y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Servicio en desarrollo", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
            }
        });
    }

    //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
    //                        .setAction("Action", null).show();

    private int findByUsername(String username) {

        int id = -1;

        Cursor cursor = db.rawQuery("SELECT ID " +
                "FROM USERS WHERE USERNAME = '" + username + "'", null);

        if (cursor.moveToFirst() == true) {
            String userId = cursor.getString(0);
            id = Integer.parseInt(userId);
        }

        return id;
    }
}
