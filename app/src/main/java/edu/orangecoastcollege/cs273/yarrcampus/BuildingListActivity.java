package edu.orangecoastcollege.cs273.yarrcampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class BuildingListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Building> buildingsList;
    private EditText searchBuildingEditText;
    private ListView buildingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buidling_list);

        searchBuildingEditText = (EditText) findViewById(R.id.searchBuildingEditText);
        buildingListView = (ListView) findViewById(R.id.BuildingListView);

        db = new DBHelper(this);
        buildingsList = db.getAllBuildings();


    }
}
