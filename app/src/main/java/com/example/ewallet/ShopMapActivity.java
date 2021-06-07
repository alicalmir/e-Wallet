package com.example.ewallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShopMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_shopmap);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.main_map);
        supportMapFragment.getMapAsync(this);

    } /*
    public void test(View view) {
        LatLng sarajevo = new LatLng(43.8563, 18.4131);
        googleMap.clear();
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(sarajevo));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sarajevo,15.0f));
    } */

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sarajevo = new LatLng(43.8563, 18.4131);
        googleMap.addMarker(new MarkerOptions().position(sarajevo).title("Marker in Sarajevo"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sarajevo));
    }



}