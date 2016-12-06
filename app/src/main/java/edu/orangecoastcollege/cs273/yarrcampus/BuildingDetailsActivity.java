package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class BuildingDetailsActivity extends AppCompatActivity {
    private TextView buildingDescDetailsTextView;
    private TextView buildingDetailsTextView;
    private ImageView buildingDetailsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);
        buildingDescDetailsTextView = (TextView) findViewById(R.id.buildingDescDetailsTextView);
        buildingDetailsTextView = (TextView) findViewById(R.id.buildingDescDetailsTextView);
        buildingDetailsImageView = (ImageView) findViewById(R.id.buildingDetailsImageView);

        Intent buildingDetails = getIntent();
        Building building =  buildingDetails.getParcelableExtra("Building");
        buildingDescDetailsTextView.setText(building.getDesc());
        buildingDetailsImageView.setImageURI(building.getImageURI());
        buildingDetailsTextView.setText(building.getName() + ":\n" + building.getCode() + "\nHours: " +
                                        building.getHours() + "\n");
    }
}
