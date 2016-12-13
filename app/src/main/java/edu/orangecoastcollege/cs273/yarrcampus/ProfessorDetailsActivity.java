package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfessorDetailsActivity extends AppCompatActivity {
    private static final int LOCATION_REQUEST_CODE = 200 ;
    private ImageView professorImageView;
    private TextView professorDetailsTextView;
    private TextView professorDescriptionTextView;
    private Button locateProfessor;
    private Professor selectedProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_details);

        professorImageView = (ImageView) findViewById(R.id.professorDetailsImageView);
        professorDetailsTextView = (TextView) findViewById(R.id.professorDetailsTextView);
        professorDescriptionTextView = (TextView) findViewById(R.id.professorDescriptionDetailsTextView);
        //locateProfessor = (Button) findViewById(R.id.locateProfessorButton);

        Intent professorDetailsIntent = getIntent();
        selectedProfessor = professorDetailsIntent.getParcelableExtra("SelectedProfessor");

        professorImageView.setImageURI(selectedProfessor.getmImageURI());
        professorDetailsTextView.setText(selectedProfessor.getmName() + "\n" +
                selectedProfessor.getmBuilding() + "\n" +
                selectedProfessor.getmOfficeHours());
        professorDescriptionTextView.setText(selectedProfessor.getmDesc());
    }

    public void locateProfessorOffice(View view)
    {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent googleMapsIntent = new Intent(this, ProfessorMapActivity.class);
            googleMapsIntent.putExtra("SelectedProfessor", selectedProfessor);
            startActivity(googleMapsIntent);
        }
    }
}
