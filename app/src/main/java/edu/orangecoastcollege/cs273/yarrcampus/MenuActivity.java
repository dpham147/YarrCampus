package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

//    private int gameCounter = 0;
//    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        //gestureDetector = new GestureDetector(this, this);

    }

    public void openBuildingSearchActivity(View view)
    {
        startActivity(new Intent(this, BuildingListActivity.class));
    }

    public void openProfessorSearchActivity(View view)
    {
        startActivity(new Intent(this, ProfessorListActivity.class));
    }

    public void openUtilitySearchActivity(View view)
    {
        startActivity(new Intent(this, UtilitySearchActivity.class));
    }

//    @Override
//    public  boolean dispatchTouchEvent(MotionEvent ev){
//        super.dispatchTouchEvent(ev);
//        return gestureDetector.onTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onDown(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        gameCounter++;
//        if (gameCounter > 10)
//        {
//            startActivity(new Intent(this, QuizActivity.class));
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        return false;
//    }
}


