package com.prod.weatherinthecities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {


    SupportMapFragment mapFragment;
    GoogleMap map;
    final String TAG = "myLogs";
    private MarkerOptions userMarker;
    OnMapReadyCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_maps);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (map == null) {
            finish();
            return;
        }
        userMarker = new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Hello world");


        init();
    }

    private void init() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(TAG, "onMapClick: " + latLng.latitude + "," + latLng.longitude);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(
                        latLng.latitude, latLng.longitude));
                userMarker = new MarkerOptions()
                        .position(new LatLng(latLng.latitude, latLng.longitude))
                        .title("Hello world");
                map.clear();
                map.addMarker(userMarker);
                map.animateCamera(cameraUpdate);
            }
        });

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.d(TAG, "onMapLongClick: " + latLng.latitude + "," + latLng.longitude);
            }
        });

//        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
//
//            @Override
//            public void onCameraChange(CameraPosition camera) {
//                Log.d(TAG, "onCameraChange: " + camera.target.latitude + "," + camera.target.longitude);
//            }
//        });
    }


    public void onClickTest(View view) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
    public void onClickPosition(View view) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(
                -27, 133));
        map.animateCamera(cameraUpdate);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
            map = googleMap;

    }
}