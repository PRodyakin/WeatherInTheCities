package com.prod.weatherinthecities;

import androidx.core.util.Consumer;

import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static org.junit.Assert.*;

public class CitiesTest {
    private static final CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void getNearbyCitiesByLatitude() {


            final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Cities changeRetr = Cities.getInstance(client);
            assertNotNull(changeRetr);


            changeRetr.getNearbyCitiesByLatitude(new Consumer<List<City>>() {
                @Override
                public void accept(List<City> cities) {

                    System.out.println(cities);
                    latch.countDown();
                }
            }, "40.74879", "-73.9845", 20);
            ;

            //latch.countDown();

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



    }
}