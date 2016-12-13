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
 * Created by roman on 11/22/2016.
 */

public class ProfessorListAdapter extends ArrayAdapter<Professor> {

    private Context mContext;
    private List<Professor> mProfessorList = new ArrayList<>();
    private  int mResourceId;
    private LinearLayout professorListLinearLayout;
    private ImageView professorListImageView;
    private TextView professorListNameTextView;
    private TextView professorListDetailsTextView;



    public ProfessorListAdapter(Context context, int resource, List<Professor> objects) {
        super(context, resource, objects);
    mContext = context;
    mResourceId = resource;
    mProfessorList = objects;
   }

    /**
     * Connects all the variables to the appropriate widgets and text files and sets the values from the object
     * into the item on the list.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        professorListLinearLayout = (LinearLayout) view.findViewById(R.id.professorListLinearView);
        professorListImageView = (ImageView) view.findViewById(R.id.professorImageView);
        professorListNameTextView = (TextView) view.findViewById(R.id.professorNameTextView);
        professorListDetailsTextView = (TextView) view.findViewById(R.id.professorDetailTextView);

        Professor selectedProfessor = mProfessorList.get(position);
        professorListLinearLayout.setTag(selectedProfessor);

        professorListNameTextView.setText(selectedProfessor.getmName());
        professorListDetailsTextView.setText(selectedProfessor.getAllDetails());
        professorListImageView.setImageURI(selectedProfessor.getmImageURI());

        return view;
    }
}
