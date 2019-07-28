package com.android.flightapp.View;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import com.android.flightapp.Model.Airport;
import com.android.flightapp.Model.AirportAdapter;
import com.android.flightapp.Model.AirportResourceModel;
import com.android.flightapp.Model.Airports;
import com.android.flightapp.Model.Coordinate;
import com.android.flightapp.Model.MyServiceHolder;
import com.android.flightapp.Presenter.OkHttpClientInstance;
import com.android.flightapp.Presenter.OnItemClickListener;
import com.android.flightapp.Presenter.api_service;
import com.android.flightapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class AirportActivity extends AppCompatActivity implements OnItemClickListener {

    List<Airport> airPortList = new ArrayList();
    RecyclerView recyclerView;
    AirportAdapter airportAdapter;
    List<String> flightSceduleCode = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport);

        recyclerView = (RecyclerView) findViewById(R.id.airport_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        airportAdapter = new AirportAdapter();
        airportAdapter.setClickListener(this);

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

        Call<AirportResourceModel> call = myService.getAirportItems();
        call.enqueue(
                new Callback<AirportResourceModel>() {
                    @Override
                    public void onResponse(Call<AirportResourceModel> call, Response<AirportResourceModel> response)
                    {
                        Log.d("Airports", "Them airports have been gotten " + response.body());
                        Airports airports = response.body().getAirportResource().getAirports() ;
                        airPortList = airports.getAirPortLists();

                        airportAdapter.setAirportList(airPortList);
                        recyclerView.setAdapter(airportAdapter);
                        Log.d("Size","This is the size of the airport list " + airPortList.size());
                        Airport airPort = airPortList.get(1);
                        String code = airPort.getAirportCode();
                        Log.d("Code","This is the airport code " + code);

                        Coordinate coordinate = airPort.getPosition();
                        float longitutde = coordinate.getCoordinateItems().getLatitude();
                        Log.d("Voila","Here is the longitude " + String.valueOf(longitutde));

                    }

                    @Override
                    public void onFailure(Call<AirportResourceModel> call, Throwable t)
                    {
                        Log.d("Airports", "Nothing gotten" + t.getMessage());
                    }
                }
        );

    }

    @Override
    public void onClick(View view, int position)
    {
        Airport airport = airPortList.get(position);
        String airportCode = airport.getAirportCode();
        Log.d("AirportCode","This is the airport code of the clicked item " + airportCode);

        flightSceduleCode.add(airportCode);
        if(flightSceduleCode.size() == 2)
        {
          Intent intent = new Intent(AirportActivity.this, FlightScheduleActivity.class);
          intent.putExtra("FirstAirportCode",flightSceduleCode.get(0));
          intent.putExtra("SecondAirportCode", flightSceduleCode.get(1));
          startActivity(intent);
        }
    }
}
