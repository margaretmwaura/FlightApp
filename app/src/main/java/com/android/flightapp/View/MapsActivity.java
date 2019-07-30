package com.android.flightapp.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.android.flightapp.Model.CoordinateItems;
import com.android.flightapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float longitutde_airport_one;
    float longitude_airport_two;
    float latitude_airport_one;
    float latitude_airport_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        Intent intent = getIntent();

        longitutde_airport_one = intent.getFloatExtra("FirstAirportLongitude",0);
        longitude_airport_two = intent.getFloatExtra("SecondAirportLongitutde",0);
        latitude_airport_one = intent.getFloatExtra("FirstAirportLatitude",0);
        latitude_airport_two = intent.getFloatExtra("SecondAirportLatitude",0);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng airportOne = new LatLng(latitude_airport_one, longitutde_airport_one);
        mMap.addMarker(new MarkerOptions().position(airportOne).title("First Airport"));

        LatLng airportTwo= new LatLng(latitude_airport_two, longitude_airport_two);
        mMap.addMarker(new MarkerOptions().position(airportTwo).title("Second airport"));

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latitude_airport_one,longitutde_airport_one), new LatLng(latitude_airport_two,longitude_airport_two))
                .width(5)
                .color(Color.BLUE));

    }
}
