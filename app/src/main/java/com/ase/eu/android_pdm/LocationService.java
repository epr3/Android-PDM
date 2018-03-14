package com.ase.eu.android_pdm;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class LocationService extends Service {
    private ArrayList<Punct> listaPuncte;
    private FusedLocationProviderClient client;
    private LocationCallback locationCallback;

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ServiceLocatie", "onStartCommand");
        listaPuncte = new ArrayList<>();
        client = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    Punct punct = new Punct(location.getLatitude(), location.getLongitude());
                    listaPuncte.add(punct);
                    Log.i("ServiceLocatie", punct.toString());
                }
            }
        };

        client.requestLocationUpdates(getLocationRequest(), locationCallback, null);



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("ServiceLocatie", "onDestroy");

        client.removeLocationUpdates(locationCallback);

        Bundle bundle = new Bundle();
        bundle.putSerializable("listaPuncte", listaPuncte);
        Intent intent = new Intent();
        intent.setAction("Add listaPuncte");
        intent.putExtra("bundle", bundle);
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("ServiceLocatie", "onBind");
        return null;
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }
}
