package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseDetailsActivity extends AppCompatActivity {

    Courses selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        ImageView courseProfImageView = (ImageView) findViewById(R.id.courseProfImageView);
        TextView courseTitleTextView = (TextView) findViewById(R.id.courseTitleTextView);
        TextView courseProfessorTextView = (TextView) findViewById(R.id.courseProfessorTextView);
        TextView crnTextView = (TextView) findViewById(R.id.crnTextView);

        Intent fromSearch = getIntent();
        selectedCourse = fromSearch.getParcelableExtra("Course");

        courseProfImageView.setImageURI(selectedCourse.getmProfessor().getmImageURI());
        courseTitleTextView.setText(selectedCourse.getCourseName());
        courseProfessorTextView.setText(selectedCourse.getmProfessor().getmName());
        crnTextView.setText(selectedCourse.getCRNString());
    }

    /**
     * Views course building info
     * @param view - the button
     */
    public void viewBuilding(View view) {
        Intent buildingDetails = new Intent(this, BuildingDetailsActivity.class);
        buildingDetails.putExtra("Building", selectedCourse.getmBuilding());
        startActivity(buildingDetails);
    }


    /**
     * Views course instructor details
     * @param view - the button
     */
    public void viewProfDetails(View view) {
        Intent professorDetailsIntent = new Intent(this, ProfessorDetailsActivity.class);
        professorDetailsIntent.putExtra("SelectedProfessor", selectedCourse.getmProfessor());
        startActivity(professorDetailsIntent);
    }
}
