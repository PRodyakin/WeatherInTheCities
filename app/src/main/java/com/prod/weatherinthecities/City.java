package com.prod.weatherinthecities;

import androidx.core.util.Consumer;

public class City {

    public String lat;
    public String lon;
    public String name;
    public Weather weatherInfo;
    public Cities cityInfo;

    public City(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public void loadCities(){
        Change changeRetr = Change.getInstance();



        changeRetr.getNearbyCitiesByLatitude(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);

            }
        }, "40.74879", "-73.9845", 20);
    }
}
