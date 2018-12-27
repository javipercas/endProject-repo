package com.example.javipercas.endprojectifp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Show_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
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
                Intent changePassword = new Intent(getApplicationContext(), Change_password.class);
                startActivity(changePassword);
                return true;
            case R.id.btEditProfile:
                Intent editProfile = new Intent(getApplicationContext(), Edit_profile.class);
                startActivity(editProfile);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
