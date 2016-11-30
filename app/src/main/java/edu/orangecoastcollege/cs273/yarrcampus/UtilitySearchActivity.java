package edu.orangecoastcollege.cs273.yarrcampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class UtilitySearchActivity extends AppCompatActivity implements OnMapReadyCallback{

    private DBHelper db;
    private List<Utility> restroomList;
    private List<Utility> waterList;
    private List<Utility> emergencyList;
    private CheckBox restroomCheck;
    private CheckBox waterFountainCheck;
    private CheckBox emergencyPhoneCheck;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_search);

        db = new DBHelper(this);
        restroomList = db.queryUtilityType("Restroom");
        waterList = db.queryUtilityType("Water Fountain");
        emergencyList = db.queryUtilityType("Emergency Phone");
        restroomCheck = (CheckBox) findViewById(R.id.restroomCheck);
        waterFountainCheck = (CheckBox) findViewById(R.id.waterFountCheck);
        emergencyPhoneCheck = (CheckBox) findViewById(R.id.emergencyPhoneCheck);

        SupportMapFragment utilityMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.utilityMapFragment);
        utilityMapFragment.getMapAsync(this);

    }

    protected void toggleRestroomPins (View view)
    {
        if (view instanceof CheckBox)
        {
            CheckBox selectedCheck = (CheckBox) view;
            if (selectedCheck.isChecked()) {
                mMap.clear();

                for (Utility utility : waterList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }

                for (Utility utility : emergencyList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }
            }
            else
            {
                for (Utility utility : restroomList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }
            }
        }
    }

    protected void toggleWaterPins (View view)
    {
        if (view instanceof CheckBox)
        {
            CheckBox selectedCheck = (CheckBox) view;
            if (selectedCheck.isChecked()) {
                mMap.clear();

                for (Utility utility : restroomList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }

                for (Utility utility : emergencyList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }
            }
            else
            {
                for (Utility utility : waterList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }
            }
        }
    }

    protected void togglePhonePins (View view)
    {
        if (view instanceof CheckBox)
        {
            CheckBox selectedCheck = (CheckBox) view;
            if (selectedCheck.isChecked()) {
                mMap.clear();

                for (Utility utility : restroomList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }

                for (Utility utility : waterList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }
            }
            else
            {
                for (Utility utility : emergencyList) {
                    LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                    mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (restroomCheck.isChecked()) {
            for (Utility utility : restroomList) {
                LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
            }
        }
        if (waterFountainCheck.isChecked()) {
            for (Utility utility : waterList) {
                LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
            }
        }
        if (emergencyPhoneCheck.isChecked()) {
            for (Utility utility : emergencyList) {
                LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
            }
        }
    }
}
