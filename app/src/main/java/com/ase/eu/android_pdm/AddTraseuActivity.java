package com.ase.eu.android_pdm;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class AddTraseuActivity extends AppCompatActivity {

    public static final String TAG = AddTraseuActivity.class.getSimpleName();

    private static final int REQUEST_CODE = 1000;

    Traseu traseu = null;
    Date dataStart = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_traseu);

        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        final TextInputLayout denumireTextLayout = findViewById(R.id.denumire);

        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getBundleExtra("bundle");
                List<Punct> listaPuncte = (List<Punct>) bundle.getSerializable("listaPuncte");
                traseu.setDataFinal(new Date());
                traseu.setListaPuncte(listaPuncte);
                traseu.setDistanta(listaPuncte.size());
                Log.i(TAG, "Lista puncte dimensiune: " + listaPuncte.size());
                Intent finishIntent = new Intent();

                finishIntent.putExtra("traseu", traseu);
                AddTraseuActivity.this.setResult(RESULT_OK, finishIntent);

                context.unregisterReceiver(this);
                AddTraseuActivity.this.finish();
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
                    traseu = new Traseu();
                    traseu.setDataStart(dataStart);
                    traseu.setDenumire(denumireText);
                    if (!checkPermissions()) {
                        Intent intent = new Intent(AddTraseuActivity.this, LocationService.class);
                        registerReceiver(broadcastReceiver, new IntentFilter("Add listaPuncte"));
                        startService(intent);
                    } else {
                        ActivityCompat.requestPermissions(AddTraseuActivity.this,
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                    }
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String denumireText = denumireTextLayout.getEditText().getText().toString();
                if (denumireText == "") {
                    denumireTextLayout.setError("Campul nu poate fi gol!");
                } else {
                    Intent intentService = new Intent(AddTraseuActivity.this, LocationService.class);
                    stopService(intentService);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(AddTraseuActivity.this, LocationService.class);
                    startService(intent);
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    // Custom Methods

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }


}
