package com.android.flightapp.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.flightapp.BuildConfig;
import com.android.flightapp.Model.Airport;
import com.android.flightapp.Model.AirportAdapter;
import com.android.flightapp.Model.Coordinate;
import com.android.flightapp.Model.CoordinateItems;
import com.android.flightapp.Model.Flight;
import com.android.flightapp.Model.FlightAdapter;
import com.android.flightapp.Model.Flights;
import com.android.flightapp.Model.MyServiceHolder;

import com.android.flightapp.Model.ScheduleResource;
import com.android.flightapp.Presenter.OkHttpClientInstance;
import com.android.flightapp.Presenter.OnItemClickListener;
import com.android.flightapp.Presenter.api_service;
import com.android.flightapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.idling.CountingIdlingResource;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class FlightScheduleActivity extends AppCompatActivity implements OnItemClickListener {

    public CountingIdlingResource idlingResource = new CountingIdlingResource("LOADER");

    String firstAirportCode;
    String secondAirportCode;
    Coordinate coordinateOne;
    CoordinateItems coordinateItemsOne;
    Coordinate coordinateTwo;
    CoordinateItems coordinateItemsTwo;
    List<Flight> flightList = new ArrayList<>();
    List<Flights> flightsList = new ArrayList<>();
    FlightAdapter flightAdapter;


    public  CountingIdlingResource getIdlingResource()
    {
        return idlingResource;
    }


    @BindView(R.id.flight_recyclerView) RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_schedule);

        ButterKnife.bind(this);
                recyclerView = (RecyclerView) findViewById(R.id.flight_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        flightAdapter = new FlightAdapter();
        flightAdapter.setClickListener(this);

        final Intent intent = getIntent();
        firstAirportCode = intent.getStringExtra("FirstAirportCode");

        secondAirportCode = intent.getStringExtra("SecondAirportCode");

        getFlightScheduleData();


    }

    @Override
    public void onClick(View view, int position)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("PREF", Context.MODE_PRIVATE);
        Float firstLongitude = sharedPreferences.getFloat("FirstAiportLongitude",0);
        Float firstLatitude = sharedPreferences.getFloat("FirstAirportLatitude",0);
        Float SecondLongitude = sharedPreferences.getFloat("SecondAiportLongitude",0);
        Float SecondLatitude = sharedPreferences.getFloat("SecondAirportLatitude",0);


        Intent intent1 = new Intent(FlightScheduleActivity.this, MapsActivity.class);
        intent1.putExtra("FirstAirportLongitude", firstLongitude);
        intent1.putExtra("FirstAirportLatitude", firstLatitude);
        intent1.putExtra("SecondAirportLongitutde",SecondLongitude);
        intent1.putExtra("SecondAirportLatitude",SecondLatitude);
        startActivity(intent1);
    }

    public void getFlightScheduleData()
    {
        idlingResource.increment();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        MyServiceHolder myServiceHolder = new MyServiceHolder();
        SharedPreferences settings = getSharedPreferences("PREFS", this.MODE_PRIVATE);
        String token = settings.getString("token", null);

        OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(this,myServiceHolder)
                .addHeader("Authorization", "Bearer "+token)
//                .addHeader("X-Originating-IP", "102.167.112.54")
                .build();



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

                if(response != null)
                {
                    Timber.d( "Gotten the scheduling data ");

                    flightsList = response.body().getSchedules().getFlights();
                    int size = response.body().getSchedules().getFlights().size();

                    for(int i = 0; i<size ; i++)
                    {
                        List<Flight> flightsOne =  flightsList.get(i).getFlights();
                        flightList.addAll(flightsOne);
                    }

                    flightAdapter.setFlightList(flightList);
                    recyclerView.setAdapter(flightAdapter);
                }
                else
                {

                    Intent intent1 = new Intent(FlightScheduleActivity.this, NoSchedulesGotten.class);
                    startActivity(intent1);
                }

                idlingResource.decrement();

            }

            @Override
            public void onFailure(Call<ScheduleResource> call, Throwable t)
            {
                Timber.d("I got nothing people" + t.getMessage());
                Intent intent1 = new Intent(FlightScheduleActivity.this,NoSchedulesGotten.class);
                startActivity(intent1);

                idlingResource.decrement();
            }
        });

    }
}
