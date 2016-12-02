package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class UtilitySearchActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final int FINE_LOCATION_REQUEST_CODE = 100;
    private static final long LOCATION_REQUEST_INTERVAL = 10000;
    private static final long LOCATION_REQUEST_FASTEST_INTERVAL = 1000;
    private DBHelper db;
    private List<Utility> allUtilities;
    private List<Utility> displayedUtilities;
    private List<Utility> restroomList;
    private List<Utility> waterList;
    private List<Utility> emergencyList;
    private CheckBox restroomCheck;
    private CheckBox waterFountainCheck;
    private CheckBox emergencyPhoneCheck;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_search);

        db = new DBHelper(this);
        restroomList = db.queryUtilityType("Restroom");
        waterList = db.queryUtilityType("Water Fountain");
        emergencyList = db.queryUtilityType("Emergency Phone");
        allUtilities = db.getAllUtilities();
        displayedUtilities = allUtilities;
        restroomCheck = (CheckBox) findViewById(R.id.restroomCheck);
        waterFountainCheck = (CheckBox) findViewById(R.id.waterFountCheck);
        emergencyPhoneCheck = (CheckBox) findViewById(R.id.emergencyPhoneCheck);

        SupportMapFragment utilityMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.utilityMapFragment);
        utilityMapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(LOCATION_REQUEST_INTERVAL)
                .setFastestInterval(LOCATION_REQUEST_FASTEST_INTERVAL);

    }

    protected void toggleRestroomPins(View view) {
        if (view instanceof CheckBox) {
            CheckBox selectedCheck = (CheckBox) view;
            mMap.clear();

            if (selectedCheck.isChecked()) {
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

    protected void toggleWaterPins(View view) {
        if (view instanceof CheckBox) {
            CheckBox selectedCheck = (CheckBox) view;
            if (selectedCheck.isChecked()) {
                mMap.clear();

                if (selectedCheck.isChecked()) {
                    for (Utility utility : waterList) {
                        LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                        mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                    }
                }
                if (restroomCheck.isChecked()) {
                    for (Utility utility : restroomList) {
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
    }

    protected void togglePhonePins(View view) {
        if (view instanceof CheckBox) {
            CheckBox selectedCheck = (CheckBox) view;
            if (selectedCheck.isChecked()) {
                mMap.clear();

                if (selectedCheck.isChecked()) {
                    for (Utility utility : emergencyList) {
                        LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
                        mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
                    }
                }
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

        LatLng currentCoord = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(currentCoord).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE);
        }
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("YarrCampus", "Connection to Google play was suspended.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("YarrCampus", "Connection to Google play has failed.");
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    private void handleLocation(Location location)
    {
        mMap.clear();
        currentLocation = location;
    }
}
