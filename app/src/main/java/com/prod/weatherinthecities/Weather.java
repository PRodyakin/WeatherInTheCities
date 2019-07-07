package com.prod.weatherinthecities;

import androidx.core.util.Consumer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Weather {

    private static Weather mInstance;
    private static final String BASE_URL = "https://api.darksky.net/";
    private static final String API_KEY = "39657af8815c46183df3cc0659ba8651";

    private Retrofit mRetrofit;

    public Weather(OkHttpClient client) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    public Weather() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .build();
    }

    private interface WeatherApi {
        @GET("forecast/{key}/{lon},{lat}")
        Call<WeatherPOJO.WeatherResult> getWeather(@Path("key") String apiKey,
                                            @Path("lon") String lon,
                                            @Path("lat") String lat,
                                            @Query("lang") String lang,
                                            @Query("exclude") String exclude,
                                            @Query("units") String units);

    }

    public class WeatherPOJO {

        public class Currently {

            @SerializedName("time")
            @Expose
            public Integer time;
            @SerializedName("summary")
            @Expose
            public String summary;
            @SerializedName("icon")
            @Expose
            public String icon;
            @SerializedName("nearestStormDistance")
            @Expose
            public Double nearestStormDistance;
            @SerializedName("nearestStormBearing")
            @Expose
            public Double nearestStormBearing;
            @SerializedName("precipIntensity")
            @Expose
            public Double precipIntensity;
            @SerializedName("precipProbability")
            @Expose
            public Double precipProbability;
            @SerializedName("temperature")
            @Expose
            public Double temperature;
            @SerializedName("apparentTemperature")
            @Expose
            public Double apparentTemperature;
            @SerializedName("dewPoint")
            @Expose
            public Double dewPoint;
            @SerializedName("humidity")
            @Expose
            public Double humidity;
            @SerializedName("pressure")
            @Expose
            public Double pressure;
            @SerializedName("windSpeed")
            @Expose
            public Double windSpeed;
            @SerializedName("windGust")
            @Expose
            public Double windGust;
            @SerializedName("windBearing")
            @Expose
            public Double windBearing;
            @SerializedName("cloudCover")
            @Expose
            public Double cloudCover;
            @SerializedName("uvIndex")
            @Expose
            public Double uvIndex;
            @SerializedName("visibility")
            @Expose
            public Double visibility;
            @SerializedName("ozone")
            @Expose
            public Double ozone;

            @Override
            public String toString() {
                return new ToStringBuilder(this).append("time", time).append("summary", summary).append("icon", icon).append("nearestStormDistance", nearestStormDistance).append("nearestStormBearing", nearestStormBearing).append("precipIntensity", precipIntensity).append("precipProbability", precipProbability).append("temperature", temperature).append("apparentTemperature", apparentTemperature).append("dewPoint", dewPoint).append("humidity", humidity).append("pressure", pressure).append("windSpeed", windSpeed).append("windGust", windGust).append("windBearing", windBearing).append("cloudCover", cloudCover).append("uvIndex", uvIndex).append("visibility", visibility).append("ozone", ozone).toString();
            }

        }

        public class WeatherResult {

            @SerializedName("latitude")
            @Expose
            public Double latitude;
            @SerializedName("longitude")
            @Expose
            public Double longitude;
            @SerializedName("timezone")
            @Expose
            public String timezone;
            @SerializedName("currently")
            @Expose
            public Currently currently;
            @SerializedName("flags")
            @Expose
            public Flags flags;
            @SerializedName("offset")
            @Expose
            public Integer offset;

            @Override
            public String toString() {
                return new ToStringBuilder(this).append("latitude", latitude).append("longitude", longitude).append("timezone", timezone).append("currently", currently).append("flags", flags).append("offset", offset).toString();
            }

        }


        public class Flags {

            @SerializedName("sources")
            @Expose
            public List<String> sources = null;
            @SerializedName("nearest-station")
            @Expose
            public Double nearestStation;
            @SerializedName("units")
            @Expose
            public String units;

            @Override
            public String toString() {
                return new ToStringBuilder(this).append("sources", sources).append("nearestStation", nearestStation).append("units", units).toString();
            }

        }

    }

    public void getWeather(final Consumer<WeatherPOJO.WeatherResult> callback , String lon, String lat, String lang, String exclude, String units) {


        Call<WeatherPOJO.WeatherResult> call = mRetrofit.create(WeatherApi.class).getWeather(API_KEY, lon, lat, lang, exclude, units);

        call.enqueue(new Callback<WeatherPOJO.WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherPOJO.WeatherResult> call, Response<WeatherPOJO.WeatherResult> response) {


                if (response.body() != null) {
                    WeatherPOJO.WeatherResult wResponse2 = response.body();
                    //responseText = ;
                    callback.accept(wResponse2);
                }
            }

            @Override
            public void onFailure(Call<WeatherPOJO.WeatherResult> call, Throwable t) {
                callback.accept( null);

            }
        });

    }


    public static Weather getInstance(OkHttpClient client) {

        if (mInstance == null) {
            mInstance = new Weather(client);
        }
        return mInstance;
    }
    public static Weather getInstance() {

        if (mInstance == null) {
            mInstance = new Weather();
        }
        return mInstance;
    }

}
