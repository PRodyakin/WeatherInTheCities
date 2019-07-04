package com.prod.weatherinthecities;

import androidx.core.util.Consumer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {

    @Test
    public void changeTest(){


        Change changeRetr = Change.getInstance();
        assertNotNull(changeRetr);
        changeRetr.getCity(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }






}