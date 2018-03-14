package com.ase.eu.android_pdm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Date;

public class AddTraseuActivity extends AppCompatActivity {

    public static final String TAG = AddTraseuActivity.class.getSimpleName();

    private static final int REQUEST_CODE = 1000;

    private ArrayList<Punct> listaPuncte = new ArrayList<>();

    Traseu traseu = null;
    Date dataStart = null;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_traseu);

        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        final TextInputLayout denumireTextLayout = findViewById(R.id.denumire);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location = locationResult.getLastLocation();

                if (location != null) {
                    Toast.makeText(AddTraseuActivity.this, "Latitude: "
                            + location.getLatitude() + ", " + "Longitude: " + location.getLongitude(), Toast.LENGTH_SHORT)
                            .show();
                    listaPuncte.add(new Punct(location.getLatitude(), location.getLongitude()));
                }
            }
        };

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String denumireText = denumireTextLayout.getEditText().getText().toString();
                if (denumireText == "") {
                    denumireTextLayout.setError("Campul nu poate fi gol!");
                } else {
                    dataStart = new Date();
                    traseu = new Traseu(denumireText, dataStart, new Date(), 0, null);
                    startLocationUpdate();
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
                    stopLocationUpdate();

                    Intent intent = new Intent();

                    intent.putExtra("traseu", traseu);
                    setResult(RESULT_OK, intent);

                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdate();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    // Custom Methods

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private void startLocationUpdate() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            fusedLocationProviderClient.requestLocationUpdates(getLocationRequest(), locationCallback, null);
        }
    }

    private void stopLocationUpdate() {
       fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }


}
