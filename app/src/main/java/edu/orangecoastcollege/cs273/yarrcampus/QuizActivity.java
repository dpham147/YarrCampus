package edu.orangecoastcollege.cs273.yarrcampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Handler;

public class QuizActivity extends AppCompatActivity {
    private DBHelper db;
    private ArrayList<Building> buildings;
    private SecureRandom random;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        buildings = db.getAllBuildings();


    }
}
