package com.ase.eu.android_pdm;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.ase.eu.android_pdm.MainActivity.traseuList;

public class AddTraseuActivity extends AppCompatActivity implements LocationProvider.LocationCallback{

    public static final String TAG = AddTraseuActivity.class.getSimpleName();

    private LocationProvider mLocationProvider;

    private ArrayList<Punct> listaPuncte = new ArrayList<>();

    Traseu traseu = null;
    Date dataStart = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_traseu);

        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        final TextInputLayout denumireTextLayout = findViewById(R.id.denumire);

        mLocationProvider = new LocationProvider(this, this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String denumireText = denumireTextLayout.getEditText().getText().toString();
                if (denumireText == "") {
                    denumireTextLayout.setError("Campul nu poate fi gol!");
                } else {
                    dataStart = new Date();
                    traseu = new Traseu(denumireText, dataStart, new Date(), 0, null);

                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String denumireText = denumireTextLayout.getEditText().getText().toString();
                if (denumireText == "") {
                    denumireTextLayout.setError("Campul nu poate fi gol!");
                } else {
                    dataStart = new Date();
                    traseu = new Traseu(denumireText, dataStart, new Date(), 0, null);
                    traseu.setListaPuncte(listaPuncte);
                    traseu.setDistanta(listaPuncte.size());
                    traseuList.add(traseu);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationProvider.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationProvider.disconnect();
    }

    public void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        listaPuncte.add(new Punct(currentLatitude, currentLongitude));
    }
}
