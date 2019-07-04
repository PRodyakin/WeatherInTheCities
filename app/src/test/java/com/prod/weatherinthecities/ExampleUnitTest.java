package com.prod.weatherinthecities;

import androidx.core.util.Consumer;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static android.os.Debug.waitForDebugger;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {

    public class Nums{
        public int number;
    }

    private static final CountDownLatch latch = new CountDownLatch(1);
    String sd;
    @Test
    public void changeTest() throws InterruptedException, IOException {




        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Change changeRetr = Change.getInstance(client);
        assertNotNull(changeRetr);


            changeRetr.getNearbyCitiesByLatitude (new Consumer<String>() {
                @Override
                public void accept(String s) {

                    sd = s;
                    System.out.println(s);
                    latch.countDown();
                }
            },"40.74879","-73.9845",20);
        ;

        //latch.countDown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


public void debbuging(){



}

}