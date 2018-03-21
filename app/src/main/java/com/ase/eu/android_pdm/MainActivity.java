package com.ase.eu.android_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<TraseuPuncte> traseuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TraseuAdapter mAdapter;
    private static int ADD_TRASEU_REQUEST_CODE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        traseuList = TraseuDatabase.getInstance(getApplicationContext()).getTraseuDAO().getAllTraseePuncte();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTraseuActivity.class);
                startActivityForResult(intent, ADD_TRASEU_REQUEST_CODE);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.traseu_recycler_view);

        mAdapter = new TraseuAdapter(traseuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_lista) {
            // Handle the camera acti
        } else if (id == R.id.nav_adaugare) {
            Intent intent = new Intent(MainActivity.this, AddTraseuActivity.class);
            startActivityForResult(intent, ADD_TRASEU_REQUEST_CODE);
        } else if (id == R.id.nav_setari) {

        } else if (id == R.id.nav_despre) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Traseu traseu = (Traseu) data.getSerializableExtra("traseu");

            TraseuPuncte traseuPuncte = new TraseuPuncte();
            traseuPuncte.setTraseu(traseu);
            traseuPuncte.setListaPuncte(traseu.getListaPuncte());
            Log.i("Numar", traseu.getListaPuncte().size() + "");
            traseuList.add(traseuPuncte);
            for(Punct p : traseu.getListaPuncte()) {
                p.setTraseuId(traseuList.size());
            }
            TraseuDatabase.getInstance(getApplicationContext()).getTraseuDAO().insertWithPuncte(traseuPuncte.getTraseu(), traseuPuncte.getListaPuncte());
            mAdapter.notifyItemInserted(traseuList.size());
        }
    }
}