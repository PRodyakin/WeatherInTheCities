package com.prod.weatherinthecities;

import androidx.core.util.Consumer;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static org.junit.Assert.*;

public class WeatherTest {

    private static final CountDownLatch latch = new CountDownLatch(1);


    @Test
    public void getWeather() {

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Weather gettingWeather = Weather.getInstance(client);
        assertNotNull(gettingWeather);


        gettingWeather.getWeather(new Consumer<Weather.WeatherPOJO.WeatherResult>() {
            @Override
            public void accept(Weather.WeatherPOJO.WeatherResult weatherPOJO) {
                System.out.println(weatherPOJO);
                latch.countDown();
            }


        }, "40.74879", "-73.9845", "ru", "minutely,hourly,daily", "si");
        ;

        //latch.countDown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}