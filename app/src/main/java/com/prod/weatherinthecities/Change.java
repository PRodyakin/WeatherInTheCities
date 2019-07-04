package com.prod.weatherinthecities;

import android.util.Log;

import androidx.core.util.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.locks.Condition;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Change {


    private static Change mInstance;
    private static final String BASE_URL = "http://getnearbycities.geobytes.com/";
    private Retrofit mRetrofit;
    public boolean isWorking = true;
    public ArrArr wResponse2;
    ////?city_coming&latitude=XXX&longitude=YYY&json&api_key=API_KEY_из_профиля

    public static class Arr {
        public List<String> arr;
    }

    public static class ArrArr {
        public List<Arr> arrArr;
    }


    public Change() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();


    }

    public void getCity(final Consumer<String> callback) {

        Call<ArrArr> call = mRetrofit.create(CitiesSearcherAPI.class).getNearbyCities(100, "69.16.219.25");

        System.out.println("asdsaaaaaaaaaa");
        call.enqueue(new Callback<ArrArr>() {
            @Override
            public void onResponse(Call<ArrArr> call, Response<ArrArr> response) {
                Log.i("autolog", "onResponse");
                if (response.body() != null) {
                    ArrArr wResponse2 = response.body();
                    System.out.println(wResponse2);
                    Log.v("respones", "ok");
                    isWorking = false;
                    //responseText = ;
                    callback.accept(wResponse2.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrArr> call, Throwable t) {
                Log.v("cities", t.getMessage());
            }
        });

    }

    public static Change getInstance() {

        if (mInstance == null) {
            mInstance = new Change();
        }
        return mInstance;
    }


}
