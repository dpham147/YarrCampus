package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

    }

    /**
     * Launches BuildingSearchActivity
     * @param view - button
     */
    public void openBuildingSearchActivity(View view)
    {
        startActivity(new Intent(this, BuildingListActivity.class));
    }

    /**
     * Launches ProfessorSearchActivity
     * @param view - button
     */
    public void openProfessorSearchActivity(View view)
    {
        startActivity(new Intent(this, ProfessorListActivity.class));
    }

    /**
     * Launches UtilitySearchActivity
     * @param view - button
     */
    public void openUtilitySearchActivity(View view)
    {
        startActivity(new Intent(this, UtilitySearchActivity.class));
    }

    /**
     * Launches CourseSearchActivity
     * @param view - button
     */
    public void openCourseSearchActivity(View view)
    {
        startActivity(new Intent(this, CoursesSearchActivity.class));
    }

    /**
     * Launches CampusContactsActivity
     * @param view - button
     */
    public void openCallContactActivity(View view){
        startActivity(new Intent(this, CampusContactsActivity.class));
    }

    public void openFaqActivity(View view){
        startActivity(new Intent(this, FaqActivity.class));
    }

    public void openContactActivity(View view)
    {
        startActivity(new Intent (this, ContactDeveloperActivity.class));
    }


}


