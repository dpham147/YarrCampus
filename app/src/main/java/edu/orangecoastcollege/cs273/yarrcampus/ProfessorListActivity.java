package edu.orangecoastcollege.cs273.yarrcampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class ProfessorListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Professor> professorsList;

    private EditText searchProfessorEditText;
    private ListView professorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_list);

        searchProfessorEditText = (EditText) findViewById(R.id.searchProfessorEditText);
        professorListView = (ListView) findViewById(R.id.ProfessorListView);

        db = new DBHelper(this);
        professorsList = db.getAllProfessors();
    }
}
