package com.ase.eu.android_pdm;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();
        List<Punct> listaPuncte = (List<Punct>) intent.getSerializableExtra("listaPuncte");
        int index = 1;
        Polyline polyline;
        PolylineOptions polylineOptions = new PolylineOptions().width(POLYLINE_STROKE_WIDTH_PX).color(Color.BLUE);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(Punct punct: listaPuncte) {
            LatLng pos = new LatLng(punct.getLatitude(), punct.getLongitude());
            builder.include(pos);
            polyline = mMap.addPolyline(polylineOptions.add(pos));
            if (index == 1 || index == listaPuncte.size()) {
                mMap.addMarker(new MarkerOptions().position(pos).title("Punct" + index));
            } else {
                mMap.addMarker(new MarkerOptions()
                        .position(pos)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .alpha(0.7f)
                        .flat(true));
            }
            index++;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));

    }
}
