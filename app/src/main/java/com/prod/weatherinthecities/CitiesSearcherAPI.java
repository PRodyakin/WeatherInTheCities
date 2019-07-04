package com.prod.weatherinthecities;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CitiesSearcherAPI {

    //@GET("GetNearbyCities?callback=?&radius={radius}&locationcode={locationcode}")
    @GET("GetNearbyCities")
    Call<List<List<String>>> getNearbyCitiesBylocationcode(@Query("radius") int radius, @Query("locationcode") String locationcode, @Query("limit") int limit);

    @GET("GetNearbyCities")
    Call<List<List<String>>> getNearbyCitiesByLatitude(@Query("radius") int radius, @Query("latitude") String latitude, @Query("longitude") String longitude, @Query("limit") int limit);
}

