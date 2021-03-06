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

    /**
     * Creates a new <code>GameListAdapter</code> given a mContext, resource id and list of games.
     *
     * @param context The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param buildings The list of buildings to display
     */
    public BuildingListAdapter(Context context, int rId, List<Building> buildings){
        super(context, rId, buildings);
        mContext = context;
        mResourceId = rId;
        mBuildingList = buildings;
    }


    /**
     * Gets the view associated with the layout.
     * @param position The position of the Building selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
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
        buildingListImageView.setImageURI(selectedBuilding.getImageURI());

        return view;
    }
}
