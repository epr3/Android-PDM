package com.ase.eu.android_pdm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Date;

import static com.ase.eu.android_pdm.MainActivity.traseuList;

public class AddTraseuActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final String TAG = AddTraseuActivity.class.getSimpleName();

    private static final int REQUEST_CODE = 1000;

    private ArrayList<Punct> listaPuncte = new ArrayList<>();

    Traseu traseu = null;
    Date dataStart = null;

    private GoogleApiClient googleApiClient;
    private Location location;

    LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_traseu);

        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        final TextInputLayout denumireTextLayout = findViewById(R.id.denumire);

        googleApiClient = new GoogleApiClient.Builder(AddTraseuActivity.this)
                .addConnectionCallbacks(AddTraseuActivity.this)
                .addConnectionCallbacks(AddTraseuActivity.this)
                .addApi(LocationServices.API)
                .build();

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
                    traseuList.add(traseu);
                    stopLocationUpdate();
                    finish();
                }
            }
        });
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "Connected");

        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(5);


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection suspended!");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed!");

        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(AddTraseuActivity.this, REQUEST_CODE);
            } catch (Exception e) {
                Log.d(TAG, e.getStackTrace().toString());
            }
        } else {
            Toast.makeText(this, "Google Play Services are not available", Toast.LENGTH_LONG)
                    .show();
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && requestCode == RESULT_OK) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            listaPuncte.add(new Punct(location.getLatitude(), location.getLongitude()));
        }
        Toast.makeText(this, "Location updated" + listaPuncte.size(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;
        fusedLocationProviderApi.removeLocationUpdates(googleApiClient, AddTraseuActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        googleApiClient.disconnect();
    }

    // Custom Methods

    private void startLocationUpdate() {

        FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;

        if (googleApiClient.isConnected()) {
            if (ActivityCompat.checkSelfPermission(AddTraseuActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(AddTraseuActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AddTraseuActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
                return;
            }
            fusedLocationProviderApi.requestLocationUpdates(googleApiClient, locationRequest, AddTraseuActivity.this);
        } else {
            googleApiClient.connect();
        }
    }

    private void stopLocationUpdate() {
        FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;
        fusedLocationProviderApi.removeLocationUpdates(googleApiClient, AddTraseuActivity.this);
    }


}
