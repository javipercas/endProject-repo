package com.example.javipercas.endprojectifp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.javipercas.endprojectifp.Utils.DataBase;

public class CreatePost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etTitle, etDescription;
    private Spinner spType;
    private Button btCancel, btSave;
    SQLiteDatabase db;
    String idUser;
    int idUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUserLogin");
        idUserLogin = Integer.parseInt(idUser);

        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();

        etTitle = (EditText)findViewById(R.id.etPostTitle);
        etDescription = (EditText)findViewById(R.id.etPostDescription);
        spType = (Spinner)findViewById(R.id.spType);
        btCancel = (Button)findViewById(R.id.btPostCancel);
        btSave = (Button)findViewById(R.id.btPostSave);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        spType.setAdapter(adapter);
        spType.setOnItemSelectedListener(this);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpLost = new Intent(CreatePost.this, HelpLost.class);
                helpLost.putExtra("idUserLogin", idUserLogin + "");
                startActivity(helpLost);
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpLost = new Intent(CreatePost.this, HelpLost.class);
                helpLost.putExtra("idUserLogin", idUserLogin + "");
                startActivity(helpLost);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
