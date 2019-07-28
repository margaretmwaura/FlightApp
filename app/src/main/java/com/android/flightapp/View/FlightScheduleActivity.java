package com.android.flightapp.View;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;

import com.android.flightapp.Model.Flight;
import com.android.flightapp.Model.MyServiceHolder;

import com.android.flightapp.Model.ScheduleResource;
import com.android.flightapp.Presenter.OkHttpClientInstance;
import com.android.flightapp.Presenter.api_service;
import com.android.flightapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlightScheduleActivity extends AppCompatActivity {

    String firstAirportCode;
    String secondAirportCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_schedule);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        Log.d("Date","String date " + String.valueOf(dateFormat.format(date)));
        Intent intent = getIntent();
        firstAirportCode = intent.getStringExtra("FirstAirportCode");
        Log.d("AirportCode","This is the starting airport code "+ firstAirportCode);
        secondAirportCode = intent.getStringExtra("SecondAirportCode");
        Log.d("SecondAirportCode","This is the second airport code " + secondAirportCode);

        MyServiceHolder myServiceHolder = new MyServiceHolder();
        SharedPreferences settings = getSharedPreferences("PREFS", this.MODE_PRIVATE);
        String token = settings.getString("token", null);

        OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(this,myServiceHolder)
                .addHeader("Authorization", "Bearer "+token)
//                .addHeader("X-Originating-IP", "102.167.112.54")
                .build();

        Log.d("MainActivity","MainActivy we are back");

        api_service myService = new retrofit2.Retrofit.Builder()
                .baseUrl("https://api.lufthansa.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(api_service.class);
        Call <ScheduleResource>call = myService.flightSchedules(firstAirportCode,secondAirportCode,String.valueOf(dateFormat.format(date)));
        call.enqueue(new Callback<ScheduleResource>() {
            @Override
            public void onResponse(Call<ScheduleResource> call, Response<ScheduleResource> response)
            {
//                Log.d("FlightSchedule","Gotten the scheduling data ");
//                int size = response.body().getSchedules().getFlights().size();
//                Log.d("SizeSchedule","This is the size of the schedule : " + String.valueOf(size));
//               Flight flight = response.body().getSchedules().getFlights().get(2).getFlights().get(1);
//                int operation = flight.getMarketCourier().getFlightNumber();
//
//                Log.d("OperstionGotten","This is the operation " + String.valueOf(operation));

                Intent intent1 = new Intent(FlightScheduleActivity.this,MapsActivity.class);
                startActivity(intent1);
            }

            @Override
            public void onFailure(Call<ScheduleResource> call, Throwable t)
            {
               Log.d("FlightSchedule","I got nothing people" + t.getMessage());
            }
        });


    }
}
