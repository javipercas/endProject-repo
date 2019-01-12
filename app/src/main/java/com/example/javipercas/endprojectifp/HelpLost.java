package com.example.javipercas.endprojectifp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.javipercas.endprojectifp.Entity.PostsEntity;
import com.example.javipercas.endprojectifp.Utils.DataBase;

import java.util.ArrayList;

public class HelpLost extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listViewLost;
    SQLiteDatabase db;
    ArrayList<PostsEntity> postsEntityList;
    SimpleCursorAdapter adapter;
    String idUser;
    int idUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_lost);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        idUser = intent.getStringExtra("idUserLogin");
        idUserLogin = Integer.parseInt(idUser);

        DataBase database = new DataBase(this);
        db = database.getWritableDatabase();

        listViewLost = (ListView)findViewById(R.id.lvLost);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Cursor cursor = db.rawQuery("SELECT _id, TITLE, DESCRIPTION, CREATE_DATE FROM POSTS WHERE VISIBLE = 1 AND TYPE = 0", null);

        String[] campos = {"TITLE", "DESCRIPTION", "CREATE_DATE"};
        int[] ids = {R.id.tvPostTitle, R.id.tvPostDescription, R.id.tvPostDate};

        adapter = new SimpleCursorAdapter(this, R.layout.list_posts, cursor, campos, ids);
        listViewLost.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPost = new Intent(HelpLost.this, CreatePost.class);
                addPost.putExtra("idUserLogin", idUserLogin + "");
                startActivity(addPost);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navLost) {
            Intent helpLost = new Intent(getApplicationContext(), HelpLost.class);
            helpLost.putExtra("idUserLogin", idUserLogin + "");
            startActivity(helpLost);

        } else if (id == R.id.navFound) {
            Intent helpFound = new Intent(getApplicationContext(), HelpFound.class);
            helpFound.putExtra("idUserLogin", idUserLogin + "");
            startActivity(helpFound);

        } else if (id == R.id.navInteresPoints) {
            Intent points = new Intent(getApplicationContext(), InteresPoints.class);
            points.putExtra("idUserLogin", idUserLogin + "");
            startActivity(points);

        } else if (id == R.id.navProfile) {
            Intent showProfile = new Intent(getApplicationContext(), ShowProfile.class);
            showProfile.putExtra("idUserLogin", idUserLogin + "");
            startActivity(showProfile);

        } else if (id == R.id.navLogOut) {
            Intent main = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(main);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}