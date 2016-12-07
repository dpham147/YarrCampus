package edu.orangecoastcollege.cs273.yarrcampus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kevin_000 on 11/16/2016.
 */

public class BuildingListAdapter extends ArrayAdapter<Building>{
    private Context mContext;
    private List<Building> mBuildingList = new ArrayList<>();
    private  int mResourceId;
    private LinearLayout buildingListLinearLayout;
    private ImageView buildingListImageView;
    private TextView buildingListNameTextView;
    private TextView buildingListCodeTextView;


    public BuildingListAdapter(Context context, int rId, List<Building> buildings){
        super(context, rId, buildings);
        mContext = context;
        mResourceId = rId;
        mBuildingList = buildings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        Building selectedBuilding = mBuildingList.get(position);

        buildingListLinearLayout = (LinearLayout) view.findViewById(R.id.buildingListLinearLayout);
        buildingListImageView = (ImageView) view.findViewById(R.id.buildingListImageView);
        buildingListNameTextView = (TextView) view.findViewById(R.id.buildingListNameTextView);
        buildingListCodeTextView = (TextView) view.findViewById(R.id.buildingListCodeTextView);

        buildingListNameTextView.setText(selectedBuilding.getName());
        buildingListCodeTextView.setText(selectedBuilding.getCode());
        buildingListLinearLayout.setTag(selectedBuilding);

        return view;
    }
}
