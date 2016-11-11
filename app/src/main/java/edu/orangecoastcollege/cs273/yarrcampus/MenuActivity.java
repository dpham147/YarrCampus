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
    }

    public void openBuildingSearchActivity(View view)
    {
        startActivity(new Intent(this, BuidlingListActivity.class));
    }

    public void openProfessorSearchActivity(View view)
    {
        startActivity(new Intent(this, ProfessorListActivity.class));
    }

    public void openUtilitySearchActivity(View view)
    {
        startActivity(new Intent(this, UtilitySearchActivity.class));
    }
}


