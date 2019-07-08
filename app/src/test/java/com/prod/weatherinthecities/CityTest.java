package com.prod.weatherinthecities;

import androidx.core.util.Consumer;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class CityTest {
    private static final CountDownLatch latch = new CountDownLatch(1);
    @Test
    public void loadCities() {

        City rostov = new City("Rostov", "-73.9845","40.74879");
        City.loadCities(rostov, new Consumer<City>() {
            @Override
            public void accept(City s) {

                latch.countDown();

            }
        });



        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void loadCitiesWeather() {

        City rostov = new City("Rostov", "-73.9845","40.74879");
        City.loadWeather(rostov, new Consumer<City>() {
            @Override
            public void accept(City s) {

                latch.countDown();

            }
        });



        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}