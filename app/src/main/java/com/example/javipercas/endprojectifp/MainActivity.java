package com.example.javipercas.endprojectifp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.javipercas.endprojectifp.Utils.DataBase;

public class MainActivity extends AppCompatActivity {

    Button btSignIn, btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase database = new DataBase(this);
        SQLiteDatabase db = database.getWritableDatabase();
        System.out.println("Se tiene que ejecutar ahorq");
        if (db != null) {
            System.out.println("PORFIIIIIIIIIINNNNNNNNNN");
        } else {
            System.out.println("puta bida no se ha ejecutado :(");
        }

//        ConnectionSQLite con = new ConnectionSQLite(this, "EndProject", null, 1)
        btSignIn = (Button) findViewById(R.id.btSignIn);
        btLogin = (Button) findViewById(R.id.btLogin);

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(getApplicationContext(), SignIn.class);
                startActivity(signIn);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ;
                Intent help = new Intent(getApplicationContext(), HelpLost.class);
                startActivity(help);

            }
        });
    }
}
