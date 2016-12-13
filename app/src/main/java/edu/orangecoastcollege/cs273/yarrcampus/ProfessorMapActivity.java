package edu.orangecoastcollege.cs273.yarrcampus;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProfessorMapActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private static final int LOCATION_REQUEST_CODE = 100;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location myLocation;
    private LocationRequest mLocationRequest;
    private Marker marker;
    private LinearLayout mapLinearLayout;

    /**
     * Creates activity_professor_map
     * creates the supportMapFragments and connects the google api client.
     * Also initializes a set interval for updating your location
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_map);

        mapLinearLayout = (LinearLayout) findViewById(R.id.activity_professor_map) ;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.officeMapFragment);
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
            mGoogleApiClient.connect();
        }


        // Define the interval for updates:
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(1000);


    }

    /**
     * stops the location update
     */
    @Override
    protected void onPause() {
        super.onPause();
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);

    }

    /**
     * resumes the location update
     */
    @Override
    protected void onResume() {
        super.onResume();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.reverse_grow_anim);
        mapLinearLayout.startAnimation(animation);

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
        if(mGoogleApiClient.isConnected())
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    /**
     * Checks for permissions and asks if permissions are not granted.
     * Sets your current location on the map
     * @param bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
        mMap.setMyLocationEnabled(true);
        if(myLocation == null)
        {
            myLocation = new Location("myLocation");
            myLocation.setLongitude(0.0f);
            myLocation.setLatitude(0.0f);
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        myLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        handleNewLocation(myLocation);
    }
    @Override
    public void onConnectionSuspended(int i) {
        Log.e("Yarr Campus", "Suspended connection from Google Play Services.");
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("Yarr Campus", "Failed connection from Google Play Services.");
    }

    /**
     * calls handleNewLocation function
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
        }

    /**
     * Adds the marker for the Professor Office location and sets the camera to that location
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent professorDetailsIntent = getIntent();
        Professor selectedProfessor = professorDetailsIntent.getParcelableExtra("SelectedProfessor");

        mMap.clear();
        LatLng officeCoordinate = new LatLng(selectedProfessor.getmOfficeLat(), selectedProfessor.getmOfficeLong());
        mMap.addMarker(new MarkerOptions()
                .position(officeCoordinate)
                .title("Current Location"));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(officeCoordinate).zoom(16.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

    }

    /**
     * deletes the old marker and creates a new one in respect to your current location.
     * @param location
     */
    public void handleNewLocation(Location location) {

        myLocation = location;
        // Add my marker
       LatLng coordinate = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        if(marker != null)
            marker.remove();
        marker = mMap.addMarker(new MarkerOptions().position(coordinate).title("You"));

    }
}

