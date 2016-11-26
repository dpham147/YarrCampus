package edu.orangecoastcollege.cs273.yarrcampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class ProfessorListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Professor> professorsList;
    private List<Professor> filteredProfessorsList;

    private EditText searchProfessorEditText;
    private ListView professorListView;

    private ProfessorListAdapter professorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_list);

        searchProfessorEditText = (EditText) findViewById(R.id.searchProfessorEditText);
        professorListView = (ListView) findViewById(R.id.ProfessorListView);

        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
       // db.importProfessors("professors")
        professorsList = db.getAllProfessors();



    }
    private String[] getInstructorNames()
    {
        String[] instructorNames = new String[professorsList.size() + 1];
        instructorNames[0] = "[Select Instructor]";
        for(int i = 1; i < instructorNames.length; ++i)
            instructorNames[i] = professorsList.get(i - 1).getmName();
        return instructorNames;
    }

    TextWatcher searchTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String input = charSequence.toString().toLowerCase();
            for(Professor p : professorsList)
            {
                for(Professor professor : professorsList)
                    filteredProfessorsList.add(professor);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}
