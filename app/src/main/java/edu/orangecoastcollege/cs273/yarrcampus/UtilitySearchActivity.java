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

import java.util.ArrayList;
import java.util.List;

// AutoCompleteTextView https://www.youtube.com/watch?v=7SVGPBxRrS0

public class UtilitySearchActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final int FINE_LOCATION_REQUEST_CODE = 100;
    private static final long LOCATION_REQUEST_INTERVAL = 10000;
    private static final long LOCATION_REQUEST_FASTEST_INTERVAL = 1000;
    protected static final float OCC_LATITUDE = 33.671028f;
    protected static final float OCC_LONGITUDE = -117.911305f;
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
        db.deleteAllUtilities();
        db.addUtility(new Utility("Restroom", 33.670791f, -117.912080f));
        db.addUtility(new Utility("Restroom", 33.668963f, -117.912321f));
        db.addUtility(new Utility("Restroom", 33.669663f, -117.909033f));
        db.addUtility(new Utility("Emergency Phone", 33.670458f, -117.908969f));
        db.addUtility(new Utility("Emergency Phone", 33.670569f, -117.911248f));
        db.addUtility(new Utility("Emergency Phone", 33.669238f, -117.911938f));
        db.addUtility(new Utility("Water Fountain", 33.668935f, -117.912281f));
        db.addUtility(new Utility("Water Fountain", 33.668025f, -117.911112f));
        db.addUtility(new Utility("Water Fountain", 33.669649f, -117.909038f));

        allUtilities = db.getAllUtilities();

        Log.i("YarrCampus", allUtilities.get(0).toString() + " in onCreate()");
        Log.i("YarrCampus", allUtilities.get(3).toString() + " in onCreate()");
        Log.i("YarrCampus", allUtilities.get(6).toString() + " in onCreate()");

        restroomList = filterUtilityList("Restroom");
        waterList = filterUtilityList("Water Fountain");
        emergencyList = filterUtilityList("Emergency Phone");
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

    protected List<Utility> filterUtilityList(String type) {
        ArrayList<Utility> filteredList = new ArrayList<>();
        for (Utility utility : allUtilities) {
            if (type.equals(utility.getmType()));
            {
                filteredList.add(utility);
            }
        }
        return filteredList;
    }

    protected void togglePins(View view) {
        if (view instanceof CheckBox) {
            handleLocation(currentLocation);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE);
        }
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        handleLocation(currentLocation);
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
        handleLocation(location);
    }

    private void handleLocation(Location location)
    {
        mMap.clear();
        currentLocation = location;
        displayedUtilities.clear();

        LatLng userCoord = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        LatLng occCoord = new LatLng(OCC_LATITUDE, OCC_LONGITUDE);
        mMap.addMarker(new MarkerOptions().position(userCoord).title("You are here"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(occCoord).zoom(16.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

        if (restroomCheck.isChecked())
        {
            for (Utility utility : restroomList)
            {
                displayedUtilities.add(utility);
            }
        }
        if (waterFountainCheck.isChecked())
        {
            for (Utility utility : waterList)
            {
                displayedUtilities.add(utility);
            }
        }
        if (emergencyPhoneCheck.isChecked())
        {
            for (Utility utility : emergencyList)
            {
                displayedUtilities.add(utility);
            }
        }

        for (Utility utility : displayedUtilities)
        {
            LatLng coordinate = new LatLng(utility.getmGPSLat(), utility.getmGPSLong());
            mMap.addMarker(new MarkerOptions().position(coordinate).title(utility.getmType()));
            Log.i("YarrCampus", utility.toString() + " in handleLocation()");
        }
    }
}














