package com.prod.weatherinthecities;

import android.util.Log;

import androidx.core.util.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Debug.waitForDebugger;


public class Cities {


    private static Cities mInstance;
    private static final String BASE_URL = "http://getnearbycities.geobytes.com/";
    private static Retrofit mRetrofit;

    ////?city_coming&latitude=XXX&longitude=YYY&json&api_key=API_KEY_из_профиля

    public Cities() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public Cities(OkHttpClient client) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


    }




    public void getNearbyCitiesByLatitude(final Consumer<List<City>> callback, String longitude, String latitude, int limit){


        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<List<String>>> call = mRetrofit.create(CitiesSearcherAPI.class).getNearbyCitiesByLatitude(10000, latitude, longitude,limit);

        getCityListString(call,callback);
    }

    public void getNearbyCitiesBylocationcode(final Consumer<List<City>> callback, String locationcode, int limit){

        Call<List<List<String>>> call = mRetrofit.create(CitiesSearcherAPI.class).getNearbyCitiesBylocationcode(10000, locationcode,limit);

        getCityListString(call,callback);
    }

    private  void getCityListString(Call<List<List<String>>> call, final Consumer<List<City>> callback) {

        //List<List<List<String>>>
        //Call<List<List<String>>> call = mRetrofit.create(CitiesSearcherAPI.class).getNearbyCitiesBylocationcode(100, "69.16.219.25");

        //System.out.println("asdsaaaaaaaaaa");
        call.enqueue(new Callback<List<List<String>>>() {
            @Override
            public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {


                if (response.body() != null && response.body().size() > 0) {
                    List<List<String>> wResponse2 = response.body();
                    //responseText = ;
                    List<City> cities = new ArrayList<City>();
                    for (int i = 0; i < response.body().size(); i++) {
                        City city = new City(
                                wResponse2.get(i).get(1),
                                wResponse2.get(i).get(8),
                                wResponse2.get(i).get(10));
                        cities.add(city);
                    }

                    callback.accept(cities);
                }
            }

            @Override
            public void onFailure(Call<List<List<String>>> call, Throwable t) {
                callback.accept( null);

            }
        });

    }

    public static Cities getInstance(OkHttpClient client) {

        if (mInstance == null) {
            mInstance = new Cities(client);
        }
        return mInstance;
    }
    public static Cities getInstance() {

        if (mInstance == null) {
            mInstance = new Cities();
        }
        return mInstance;
    }


}
