package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 5000;
    private TextView splashTipTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        splashTipTextView = (TextView) findViewById(R.id.splashTipTextView);

        String[] allTips = this.getResources().getStringArray(R.array.splash_screen_tips_list);
        Random rng = new Random();
        int randomHint = rng.nextInt(allTips.length);
        splashTipTextView.setText(allTips[randomHint]);


        TimerTask menuTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this, MenuActivity.class));
            }
        };

        Timer timer = new Timer();
        timer.schedule(menuTask, SPLASH_DELAY);
    }
}
