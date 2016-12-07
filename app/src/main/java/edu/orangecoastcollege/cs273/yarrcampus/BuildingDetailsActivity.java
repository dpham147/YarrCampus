package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class BuildingDetailsActivity extends AppCompatActivity {
    private TextView buildingDetailsTextView;
    private ImageView buildingDetailsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);
        buildingDetailsTextView = (TextView) findViewById(R.id.buildingDetailsTextView);
        buildingDetailsImageView = (ImageView) findViewById(R.id.buildingDetailsImageView);

        Intent buildingDetails = getIntent();
        Building building =  (Building) buildingDetails.getParcelableExtra("Building");
        buildingDetailsImageView.setImageURI(building.getImageURI());
        buildingDetailsTextView.setText(building.getName() + ":\n" + building.getCode() + "\nHours: " +
                                        building.getHours() + "\n");
    }
}
