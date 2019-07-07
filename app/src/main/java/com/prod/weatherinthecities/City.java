package com.prod.weatherinthecities;

import androidx.core.util.Consumer;

import java.util.List;

public class City {

    public String lat;
    public String lon;
    public String name;
    public int limit;
    public Weather.WeatherPOJO.WeatherResult weatherInfo;
    public List<City> nearestCities;

    public City(String name, String lat, String  lon) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;

        this.limit = 20;
    }

    public void setnearestCities(List<City> nearestCities){
        this.nearestCities = nearestCities;

    }

    public static void loadCities(final City city, final Consumer<City> consumerCity){

        Cities citiesService = new Cities();

        citiesService.getNearbyCitiesByLatitude(new Consumer<List<City>>() {
            @Override
            public void accept(List<City> s) {
                city.nearestCities = s;
                consumerCity.accept(city);
            }
        }, city.lat, city.lon, city.limit );

    }

    public static void loadWeather(final City city, final Consumer<City> consumerCity){

        Weather weatherService = Weather.getInstance();

        weatherService.getWeather(new Consumer<Weather.WeatherPOJO.WeatherResult>() {
            @Override
            public void accept(Weather.WeatherPOJO.WeatherResult weatherPOJO) {
                city.weatherInfo = weatherPOJO;
                consumerCity.accept(city);

            }

        },city.lat,  city.lon, "ru", "minutely,hourly,daily", "si");
    }


}
