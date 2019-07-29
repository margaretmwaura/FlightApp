package com.android.flightapp.Presenter;

import com.android.flightapp.Model.AirportResourceModel;
import com.android.flightapp.Model.RetrofitResponse;
import com.android.flightapp.Model.ScheduleResource;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface api_service
{
    @FormUrlEncoded
    @POST("v1/oauth/token")
    Call<RetrofitResponse> refreshToken(@Field("client_id") String client_id , @Field("client_secret") String client_secret , @Field("grant_type") String path);

    @Headers("Accept: application/json")
    @GET("/v1/references/airports?limit=90&offset=056&LHoperated=1&lang=en")
    Call<AirportResourceModel> getAirportItems();

    @Headers("Accept: application/json")
    @GET("/v1/operations/schedules/{origin}/{destination}/{fromDateTime}?limit=99&offset=0&directFlights=0")
    Call <ScheduleResource>flightSchedules(@Path("origin")String origin, @Path("destination")String destination, @Path("fromDateTime")String fromDate);
}
