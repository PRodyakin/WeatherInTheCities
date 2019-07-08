package com.prod.weatherinthecities;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    public City city;
    SupportMapFragment mapFragment;
    GoogleMap map;
    final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_g_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

    }

    private void init() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(TAG, "onMapClick: " + latLng.latitude + "," + latLng.longitude);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(
                        latLng.latitude, latLng.longitude));
                MarkerOptions userMarker = new MarkerOptions()
                        .position(new LatLng(latLng.latitude, latLng.longitude))
                        .title("Ваша точка")
                        .icon(
                                BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                map.clear();
                map.addMarker(userMarker);
                map.animateCamera(cameraUpdate);


                city = new City("Default", Double.toString(userMarker.getPosition().longitude), Double.toString(userMarker.getPosition().latitude));

                City.loadCities(city,new Consumer<City>() {
                    @Override
                    public void accept(City city) {

                        for (City nearestCity : city.nearestCities) {
                            MarkerOptions cityMarkerOptions = new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble(nearestCity.lat), Double.parseDouble(nearestCity.lon)))
                                    .title(nearestCity.name);
                            map.addMarker(cityMarkerOptions);

                        }
                    }
                });

//                cities.getNearbyCitiesByLatitude(new Consumer<List<City>>() {
//                    @Override
//                    public void accept(List<City> cities) {
//
//                        for (City city : cities) {
//                            MarkerOptions cityMarkerOptions = new MarkerOptions()
//                                    .position(new LatLng(Double.parseDouble(city.lat), Double.parseDouble(city.lon)))
//                                    .title(city.name);
//                            map.addMarker(cityMarkerOptions);
//
//                        }
//                    }
//                }, city.lat, city.lon, 20);
            }
        });
/*
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
//        });*/
    }







    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (map == null) {
            finish();
            return;
        }

        //        MarkerOptions marker = new MarkerOptions()
        //                .position(new LatLng(47.2313, 39.7233))
        //                .title("Hello world");
        //        map.addMarker(marker);
        //
        //        Cities cities = Cities.getInstance();
        //        City city = new City("Rostov", Double.toString(marker.getPosition().latitude), Double.toString(marker.getPosition().longitude));
        //
        //        cities.getNearbyCitiesByLatitude(new Consumer<List<City>>() {
        //            @Override
        //            public void accept(List<City> cities) {
        //                for (City city : cities) {
        //                    MarkerOptions cityMarkerOptions = new MarkerOptions()
        //                            .position(new LatLng(Double.parseDouble(city.lat), Double.parseDouble(city.lon)))
        //                            .title(city.name);
        //                    map.addMarker(cityMarkerOptions);
        //
        //                }
        //            }
        //        }, city.lon, city.lat, 20);
        //
        //
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                             @Override
                                             public void onInfoWindowClick(final Marker marker) {
                                                 Weather gettingWeather = Weather.getInstance();
                                                 gettingWeather.getWeather(new Consumer<Weather.WeatherPOJO.WeatherResult>() {
                                                     @Override
                                                     public void accept(Weather.WeatherPOJO.WeatherResult weatherPOJO) {
                                                         marker.setSnippet(weatherPOJO.currently.temperature+" °С, "+ weatherPOJO.currently.summary );
                                                         marker.showInfoWindow();
                                                     }




                                                 }, Double.toString(marker.getPosition().latitude), Double.toString(marker.getPosition().longitude), "ru", "minutely,hourly,daily", "si");
                                             }
                                         }
        );


        init();
    }
}