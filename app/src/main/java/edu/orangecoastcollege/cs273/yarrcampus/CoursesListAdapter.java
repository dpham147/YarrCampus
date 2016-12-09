package edu.orangecoastcollege.cs273.yarrcampus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpham147 on 12/8/2016.
 */

public class CoursesListAdapter extends ArrayAdapter<Courses> {

    private Context mContext;
    private List<Courses> mCoursesList = new ArrayList<>();
    private int mResourceId;


    public CoursesListAdapter(Context context, int resource, List<Courses> courses) {
        super(context, resource, courses);
        mContext = context;
        mResourceId = resource;
        mCoursesList = courses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        return view;
    }
}
