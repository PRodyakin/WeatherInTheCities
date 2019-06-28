package com.prod.weatherinthecities;

import java.util.List;

import retrofit2.http.GET;

public class CitiesSearcherAPI {

    public class City{



    }


    public interface ServerApi {

        @GET("GetNearbyCities")
        List<City> getCities();

    }
}
