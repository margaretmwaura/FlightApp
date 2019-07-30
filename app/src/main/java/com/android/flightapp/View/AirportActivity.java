package com.android.flightapp.View;

import android.content.Context;
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
import androidx.test.espresso.idling.CountingIdlingResource;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;
//import androidx.test.espresso.idling.CountingIdlingResource;

public class AirportActivity extends AppCompatActivity implements OnItemClickListener {


    public CountingIdlingResource idlingResource = new CountingIdlingResource("LOADER");


    List<Airport> airPortList = new ArrayList();
    @BindView(R.id.airport_recyclerView)RecyclerView recyclerView;
    AirportAdapter airportAdapter;
    List<Airport> flightSceduleCode = new ArrayList();

    public  CountingIdlingResource getIdlingResource()
    {
        return idlingResource;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport);

        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        airportAdapter = new AirportAdapter();
        airportAdapter.setClickListener(this);
        recyclerView.setAdapter(airportAdapter);

        getAirportData();

    }

    @Override
    public void onClick(View view, int position)
    {
        Airport airport = airPortList.get(position);
        String airportCode = airport.getAirportCode();
        Timber.d("This is the airport code of the clicked item " + airportCode);

        flightSceduleCode.add(airport);
        if(flightSceduleCode.size() == 2)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("PREF", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("FirstAiportLongitude",flightSceduleCode.get(0).getPosition().getCoordinateItems().getLongitude());
            editor.putFloat("FirstAirportLatitude",flightSceduleCode.get(0).getPosition().getCoordinateItems().getLatitude());
            editor.putFloat("SecondAiportLongitude",flightSceduleCode.get(1).getPosition().getCoordinateItems().getLongitude());
            editor.putFloat("SecondAirportLatitude",flightSceduleCode.get(1).getPosition().getCoordinateItems().getLatitude());
            editor.commit();

          Intent intent = new Intent(AirportActivity.this, FlightScheduleActivity.class);
          intent.putExtra("FirstAirportCode",flightSceduleCode.get(0).getAirportCode());
          intent.putExtra("SecondAirportCode", flightSceduleCode.get(1).getAirportCode());
          startActivity(intent);
        }


    }

    public void getAirportData()
    {
        idlingResource.increment();
        MyServiceHolder myServiceHolder = new MyServiceHolder();
        SharedPreferences settings = getSharedPreferences("PREFS", this.MODE_PRIVATE);
        String token = settings.getString("token", null);

        OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(this,myServiceHolder)
                .addHeader("Authorization", "Bearer "+token)
                .build();


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

                        Airports airports = response.body().getAirportResource().getAirports();
                        airPortList = airports.getAirPortLists();

                        airportAdapter.setAirportList(airPortList);
                        recyclerView.setAdapter(airportAdapter);
                        Timber.d("This is the size of the airport list " + airPortList.size());
                        Airport airPort = airPortList.get(1);
                        String code = airPort.getAirportCode();


                        Coordinate coordinate = airPort.getPosition();
                        float longitutde = coordinate.getCoordinateItems().getLatitude();
                        idlingResource.decrement();

                    }

                    @Override
                    public void onFailure(Call<AirportResourceModel> call, Throwable t)
                    {
                        Timber.d( "Nothing gotten" + t.getMessage());
                        idlingResource.decrement();
                    }
                }
        );
    }
}
