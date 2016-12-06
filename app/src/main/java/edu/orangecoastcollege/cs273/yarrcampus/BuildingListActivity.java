package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BuildingListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Building> allBuildingsList;
    private List<Building> filteredBuildingsList;
    private EditText searchBuildingEditText;
    private ListView buildingListView;
    private BuildingListAdapter buildingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buidling_list);

        searchBuildingEditText = (EditText) findViewById(R.id.searchBuildingEditText);
        searchBuildingEditText.addTextChangedListener(buildingNameTextWatcher);
        buildingListView = (ListView) findViewById(R.id.BuildingListView);


        db = new DBHelper(this);
        allBuildingsList = db.getAllBuildings();
        filteredBuildingsList = new ArrayList<>(allBuildingsList);

        buildingListAdapter = new BuildingListAdapter(this, R.layout.building_list_item, filteredBuildingsList);
        buildingListView.setAdapter(buildingListAdapter);



    }

    public TextWatcher buildingNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String input = s.toString().toLowerCase();
            if(input.equals("")){
                buildingListAdapter.clear();
                for (Building building : allBuildingsList)
                    buildingListAdapter.add(building);
            }
            else{
                buildingListAdapter.clear();
                for(Building building : allBuildingsList){
                    String buildingName = building.getName().toLowerCase();
                    String buildingCode = building.getCode().toLowerCase();
                    if(buildingName.contains(input) || buildingCode.contains(input))
                        buildingListAdapter.add(building);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void viewBuildingDetailsActivity(View view){
        Intent buildingDetails = new Intent(this, BuildingDetailsActivity.class);
        Building building = (Building) view.getTag();
        buildingDetails.putExtra("Building", building);
        startActivity(buildingDetails);
    }
}
