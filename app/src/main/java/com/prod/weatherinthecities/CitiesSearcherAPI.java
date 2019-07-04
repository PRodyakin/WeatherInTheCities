package com.prod.weatherinthecities;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CitiesSearcherAPI {

    //@GET("GetNearbyCities?callback=?&radius={radius}&locationcode={locationcode}")
    @GET("GetNearbyCities")
    Call<Change.ArrArr> getNearbyCities(@Query("radius") int radius, @Query("locationcode") String locationcode);
}

