package edu.orangecoastcollege.cs273.yarrcampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.util.List;

public class UtilitySearchActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Utility> restroomList;
    private List<Utility> waterList;
    private List<Utility> emergencyList;
    private CheckBox restroomCheck;
    private CheckBox waterFountainCheck;
    private CheckBox emergencyPhoneCheck;

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


    }

    protected void toggleRestroomPins (View view)
    {
        if (view instanceof CheckBox)
        {
            CheckBox selectedCheck = (CheckBox) view;
        }
    }

    protected void toggleWaterPins (View view)
    {
        if (view instanceof CheckBox)
        {
            CheckBox selectedCheck = (CheckBox) view;
        }
    }

    protected void togglePhonePins (View view)
    {
        if (view instanceof CheckBox)
        {
            CheckBox selectedCheck = (CheckBox) view;
        }
    }
}
