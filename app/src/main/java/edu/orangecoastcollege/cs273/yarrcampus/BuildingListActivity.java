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

import static edu.orangecoastcollege.cs273.yarrcampus.ProfessorListActivity.getUriResource;

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
        db.deleteAllBuildings();
        db.addBuilding(new Building("Watson Hall", "WTNH", "9:00 AM - 6:00 PM", getUriResource(this, R.drawable.basic_building), 33.670686f,  -117.909255f));
        db.addBuilding(new Building("Math Business and Computing Center", "MBCC", "9:00 AM - 10:00 PM", getUriResource(this, R.drawable.basic_building), 33.670797f, -117.912142f));
        db.addBuilding(new Building("Chemistry", "CHEM", "9:00 AM - 6:00 PM", getUriResource(this, R.drawable.basic_building), 33.671648f, -117.914652f));
        db.addBuilding(new Building("Library", "LIBR", "9:00 AM - 10:00 PM", getUriResource(this, R.drawable.basic_building), 33.668890f, -117.912638f));
        db.addBuilding(new Building("Robert Moore Theater", "RMTH", "9:00 AM - 10:00 PM", getUriResource(this, R.drawable.basic_building), 33.668953f, -117.909493f));
        db.addBuilding(new Building("Social Science", "SCLS", "9:00 AM - 10:00 PM", getUriResource(this, R.drawable.basic_building), 33.670292f, -117.910308f));
        db.addBuilding(new Building("Writer's Row", "WTRW", "9:00 AM - 10:00 PM", getUriResource(this, R.drawable.basic_building), 33.670075f, -117.912320f));
        allBuildingsList = db.getAllBuildings();
        filteredBuildingsList = new ArrayList<> (allBuildingsList);

        buildingListAdapter = new BuildingListAdapter(this, R.layout.building_list_item, filteredBuildingsList);
        buildingListView.setAdapter(buildingListAdapter);
    }

    /**
     * Whenever the user enters text into the edit text, filter out all the buildings
     * that contain whatever is in the edit text. Checks both the building name
     * and the building code
     */
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

    /**
     * When the user clicks on a building in the list view, start up the details activity and pass
     * the selected building to the activity
     *
     * @param view The current view
     */
    public void viewBuildingDetailsActivity(View view){
        Intent buildingDetails = new Intent(this, BuildingDetailsActivity.class);
        Building building = (Building) view.getTag();
        buildingDetails.putExtra("Building", building);
        startActivity(buildingDetails);
    }

    /**
     * Goes to the Map Activity when the user clicks on the button
     *
     * @param  view The current view
     */
    public void goToMapView(View view){
        startActivity(new Intent(this, BuildingMapViewActivity.class) );
    }

}
