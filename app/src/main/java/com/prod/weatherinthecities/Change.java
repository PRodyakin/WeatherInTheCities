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

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Debug.waitForDebugger;


public class Change {


    private static Change mInstance;
    private static final String BASE_URL = "http://getnearbycities.geobytes.com/";
    private Retrofit mRetrofit;

    ////?city_coming&latitude=XXX&longitude=YYY&json&api_key=API_KEY_из_профиля

    public Change() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public Change(OkHttpClient client) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


    }
   /* public void getCity(final Consumer<String> callback) {

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

    }*/

    public void getNearbyCitiesByLatitude(final Consumer<String> callback, String longitude, String latitude, int limit){

        Call<List<List<String>>> call = mRetrofit.create(CitiesSearcherAPI.class).getNearbyCitiesByLatitude(200, longitude, latitude,limit);

        getCityListString(call,callback);
    }


   public void getNearbyCitiesBylocationcode(final Consumer<String> callback, String locationcode, int limit){

       Call<List<List<String>>> call = mRetrofit.create(CitiesSearcherAPI.class).getNearbyCitiesBylocationcode(200, locationcode,limit);

       getCityListString(call,callback);
   }

    private void getCityListString(Call<List<List<String>>> call, final Consumer<String> callback) {

        //List<List<List<String>>>
        //Call<List<List<String>>> call = mRetrofit.create(CitiesSearcherAPI.class).getNearbyCitiesBylocationcode(100, "69.16.219.25");

        //System.out.println("asdsaaaaaaaaaa");
        call.enqueue(new Callback<List<List<String>>>() {
            @Override
            public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {


                if (response.body() != null) {
                    List<List<String>> wResponse2 = response.body();
                    //responseText = ;
                    callback.accept(wResponse2.toString());
                }
            }

            @Override
            public void onFailure(Call<List<List<String>>> call, Throwable t) {
                callback.accept( t.getMessage());

            }
        });

    }


    public static Change getInstance(OkHttpClient client) {

        if (mInstance == null) {
            mInstance = new Change(client);
        }
        return mInstance;
    }


}
