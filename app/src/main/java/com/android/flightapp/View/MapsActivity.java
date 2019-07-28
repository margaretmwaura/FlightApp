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
        CoordinateItems firstAirport = intent.getParcelableExtra("FirstAirport");
        CoordinateItems secondAirport  = intent.getParcelableExtra("SecondAirport");
        longitutde_airport_one = firstAirport.getLongitude();
        longitude_airport_two = secondAirport.getLongitude();
        latitude_airport_one = firstAirport.getLatitude();
        latitude_airport_two = secondAirport.getLatitude();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng airportOne = new LatLng(latitude_airport_one, longitutde_airport_one);
        mMap.addMarker(new MarkerOptions().position(airportOne).title("Marker in Sydney"));

        LatLng airportTwo= new LatLng(latitude_airport_two, longitude_airport_two);
        mMap.addMarker(new MarkerOptions().position(airportTwo).title("Marker in Sydney"));

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(latitude_airport_one,longitutde_airport_one), new LatLng(latitude_airport_two,longitude_airport_two))
                .width(5)
                .color(Color.BLUE));

    }
}
