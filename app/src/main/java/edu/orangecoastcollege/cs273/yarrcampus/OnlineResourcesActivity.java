package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OnlineResourcesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_resources);
    }

    public void openOccMainWebsite(View view){
        Intent test = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.orangecoastcollege.edu/Pages/home.aspx"));
        startActivity(test);
    }

    public void openBlackboardWebsite(View view){
        Intent test = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://occ.blackboard.com"));
        startActivity(test);
    }

    public void openMyoccWebsite(View view){
        Intent test = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://mycoast.cccd.edu"));
        startActivity(test);
    }

    public void openCanvasWebsite(View view){
        Intent test = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://canvas.cccd.edu"));
        startActivity(test);
    }
}
