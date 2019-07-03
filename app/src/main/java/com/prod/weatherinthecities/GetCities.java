package com.prod.weatherinthecities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GetCities {

    private static GetCities mInstance;
    private static  final String BASE_URL = "http://htmlweb.ru/geo/api.php";
    private Retrofit mRetrofit;
    ////?city_coming&latitude=XXX&longitude=YYY&json&api_key=API_KEY_из_профиля

    private GetCities(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static GetCities getInstance(){

        if (mInstance == null) {
            mInstance = new GetCities();
        }
        return mInstance;
    }





}
