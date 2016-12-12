package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static edu.orangecoastcollege.cs273.yarrcampus.UtilitySearchActivity.OCC_LATITUDE;
import static edu.orangecoastcollege.cs273.yarrcampus.UtilitySearchActivity.OCC_LONGITUDE;

public class BuildingMapViewActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnInfoWindowClickListener{

    private GoogleMap campusMap;
    private static final int FINE_LOCATION_REQUEST_CODE = 100;
    private DBHelper db;
    private ArrayList<Building> allBuildingsList;
    private GoogleApiClient mGoogleApiClient;

    /**
     * Loads up the Google Maps centered on Orange Coast College
     * and asks for permission to get the current location of
     * user.
     *
     * @param savedInstanceState Last instance of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_map_view);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.buildingsFragment);
        mapFragment.getMapAsync(this);

        if(mGoogleApiClient == null){
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
            mGoogleApiClient.connect();
        }

    }

    /**
     * Empty the map and resets the the location of the map onto Orange Coast College.
     * Gets all the buildings at and puts a marker above the location. Also every marker
     * contains the building below it.
     *
     * @param bundle
     */
    public void onConnected(@Nullable Bundle bundle) {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE);
        }

        campusMap.clear();
        LatLng buildingLocation = new LatLng(OCC_LATITUDE, OCC_LONGITUDE);
        CameraPosition buildingPosition = new CameraPosition.Builder().target(buildingLocation).zoom(16.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(buildingPosition);
        campusMap.moveCamera(cameraUpdate);
        db = new DBHelper(this);
        allBuildingsList = db.getAllBuildings();
        for (Building building : allBuildingsList){
            LatLng temp = new LatLng(building.getGPSLat(), building.getGPSLong());
            campusMap.addMarker(new MarkerOptions().position(temp).title(building.getName())).setTag(building);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("Yarr Campus", "Suspended connection from Google Play Services.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("Yarr Campus", "Failed connection from Google Play Services.");
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        campusMap = googleMap;

        campusMap.setOnInfoWindowClickListener(this);
    }

    /**
     * When the window above the marker is clicked start up the details activity.
     * When starting the activity send over the building that was included with
     * the marker.
     *
     * @param marker Marker above the location
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent buildingDetails = new Intent(this, BuildingDetailsActivity.class);
        buildingDetails.putExtra("Building", (Building) marker.getTag());
        startActivity(buildingDetails);
    }
}
