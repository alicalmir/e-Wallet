package com.example.ewallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ShopMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private SupportMapFragment supportMapFragment;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_shopmap);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.main_map);
        supportMapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap = googleMap;

        Intent intent = getIntent();
        String location=intent.getStringExtra(MainActivity.LOC_MESSAGE);

        try {
            List<Address> addresses = geocoder.getFromLocationName(location,1);

            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                LatLng coordinates = new LatLng(address.getLatitude(),address.getLongitude());

                MarkerOptions markerOptions = new MarkerOptions().position(coordinates).title(address.getLocality());
                googleMap.addMarker(markerOptions);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates,16));

            }

        } catch (IOException e) {
            Toast.makeText(this, "The location does not exist", Toast.LENGTH_SHORT).show();
        }


    }



}