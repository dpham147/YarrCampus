package edu.orangecoastcollege.cs273.yarrcampus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.google.android.gms.maps.model.MarkerOptions;

public class BuildingDetailsActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{

    private int FINE_LOCATION_REQUEST_CODE = 100;
    private TextView buildingNameDetailsTextView;
    private TextView buildingCodeDetailsTextView;
    private TextView buildingHoursDetailsTextView;
    private ImageView buildingDetailsImageView;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);
        buildingNameDetailsTextView = (TextView) findViewById(R.id.buildingNameDetailsTextView);
        buildingCodeDetailsTextView = (TextView) findViewById(R.id.buildingCodeDetailsTextView);
        buildingHoursDetailsTextView = (TextView) findViewById(R.id.buildingHoursDetailsTextView);
        buildingDetailsImageView = (ImageView) findViewById(R.id.buildingDetailsImageView);

        Intent buildingDetails = getIntent();
        Building building =  buildingDetails.getParcelableExtra("Building");
        buildingDetailsImageView.setImageURI(building.getImageURI());
        buildingNameDetailsTextView.setText(building.getName());
        buildingCodeDetailsTextView.setText(building.getCode());
        buildingHoursDetailsTextView.setText(building.getHours());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.buildingMapFragment);
        mapFragment.getMapAsync(this);

        if(mGoogleApiClient == null){
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                                .addConnectionCallbacks(this)
                                .addOnConnectionFailedListener(this)
                                .addApi(LocationServices.API).build();
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE);
        }

        Intent buildingDetails = getIntent();
        Building building =  buildingDetails.getParcelableExtra("Building");
        mMap.clear();
        LatLng buildingLocation = new LatLng(building.getGPSLat(), building.getGPSLong());
        CameraPosition buildingPosition = new CameraPosition.Builder().target(buildingLocation).zoom(18.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(buildingPosition);
        mMap.addMarker(new MarkerOptions().position(buildingLocation).title(building.getName()));
        mMap.moveCamera(cameraUpdate);
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
        mMap = googleMap;
    }
}
