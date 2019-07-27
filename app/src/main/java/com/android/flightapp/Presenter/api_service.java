package com.android.flightapp.Presenter;

import com.android.flightapp.Model.AirportResourceModel;
import com.android.flightapp.Model.RetrofitResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface api_service
{
    @FormUrlEncoded
    @POST("v1/oauth/token")
    Call<RetrofitResponse> refreshToken(@Field("client_id") String client_id , @Field("client_secret") String client_secret , @Field("grant_type") String path);

    @Headers("Accept: application/json")
    @GET("mds-references/airports/?limit=44&offset=0&LHoperated=0")
    Call<AirportResourceModel> getAirportItems();
}
