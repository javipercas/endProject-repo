package com.example.javipercas.endprojectifp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.javipercas.endprojectifp.Utils.DataBase;
import com.example.javipercas.endprojectifp.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatePets extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    EditText etPetName, etPetAge, etPetRace, etPetColor, etPetCharacter;
    Spinner spSex, spSterilized;
    Button btAddPet, btPetCancel;
    SQLiteDatabase db;
    String idUser;
    int idUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pets);

        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();

        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUserLogin");
        idUserLogin = Integer.parseInt(idUser);

        etPetName = (EditText) findViewById(R.id.etPetName);
        etPetAge = (EditText)findViewById(R.id.etPetAge);
        etPetRace = (EditText)findViewById(R.id.etPetRace);
        etPetColor = (EditText)findViewById(R.id.etPetColor);
        etPetCharacter = (EditText)findViewById(R.id.etPetCharacter);
        spSex = (Spinner)findViewById(R.id.spSex);
        spSterilized = (Spinner)findViewById(R.id.spSterilized);
        btAddPet = (Button)findViewById(R.id.btAddPet);
        btPetCancel = (Button)findViewById(R.id.btPetCancel);

        ArrayAdapter<CharSequence> adapterSex = ArrayAdapter.createFromResource(this, R.array.Sex, android.R.layout.simple_spinner_item);
        spSex.setAdapter(adapterSex);
        spSex.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterSterilized = ArrayAdapter.createFromResource(this, R.array.Sterilizeded, android.R.layout.simple_spinner_item);
        spSterilized.setAdapter(adapterSterilized);
        spSterilized.setOnItemSelectedListener(this);

        btAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePet(etPetName.getText().toString(),
                        etPetAge.getText().toString(),
                        spSex.getSelectedItemPosition(),
                        spSex.getSelectedItemPosition(),
                        etPetRace.getText().toString(),
                        etPetColor.getText().toString(),
                        etPetCharacter.getText().toString(),
                        idUserLogin);
                Intent showProfile = new Intent(CreatePets.this, ShowProfile.class);
                showProfile.putExtra("idUserLogin", idUserLogin + "");
                startActivity(showProfile);
            }
        });

        btPetCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showProfile = new Intent(CreatePets.this, ShowProfile.class);
                showProfile.putExtra("idUserLogin", idUserLogin + "");
                startActivity(showProfile);
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

    private void savePet(String name, String age, int sex, int sterilized, String race, String color, String character, int userId) {
        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.Pets.PETS_NAME, name);
        contentValues.put(Utils.Pets.PETS_AGE, age);
        contentValues.put(Utils.Pets.PETS_SEX, sex);
        contentValues.put(Utils.Pets.PETS_STERILIZED, sterilized);
        contentValues.put(Utils.Pets.PETS_RACE, race);
        contentValues.put(Utils.Pets.PETS_COLOR, color);
        contentValues.put(Utils.Pets.PETS_CHARACTER, character);
        contentValues.put(Utils.Pets.PETS_USER_ID, userId);

        long result = db.insert("PETS", null, contentValues);

        Toast.makeText(CreatePets.this, "Mascota agregada", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
