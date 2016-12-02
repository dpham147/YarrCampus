package edu.orangecoastcollege.cs273.yarrcampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class ProfessorListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Professor> professorsList;
    private List<Building> buildingList;

    private EditText searchProfessorEditText;
    private ListView professorListView;
    private List<Professor> filteredProfessors;

    private ProfessorListAdapter professorListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_list);


        db = new DBHelper(this);
        // db.importProfessors("professors.csv")
        professorsList = db.getAllProfessors();
        buildingList = db.getAllBuildings();


        filteredProfessors = new ArrayList<>(professorsList);
        searchProfessorEditText = (EditText) findViewById(R.id.searchProfessorEditText);
        professorListView = (ListView) findViewById(R.id.ProfessorListView);
        professorListAdapter =
                new ProfessorListAdapter(this, R.layout.professor_list_item, filteredProfessors);
        professorListView.setAdapter(professorListAdapter);









    }
    /*
    private String[] getCoursesName()
    {
        String[] coursesNames = new String[allCoursesList.size() + 1];
        coursesNames[0] = "[Select Course]";
        for(int i = 1; i < coursesNames.length; ++i)
            coursesNames[i] = allCoursesList.get(i - 1).getCourseName();
        return coursesNames;
    }
    */

    TextWatcher searchTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String input = charSequence.toString().toLowerCase();

            professorListAdapter.clear();
            for(Professor p : professorsList)
            {
                if(p.getmName().toLowerCase().contains(input) )
                {
                    professorListAdapter.add(p);
                }
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
/*
    private String[] getCourcesName()
    {
        String[] instructorNames = new String[allCoursesList.size() + 1];
        instructorNames[0] = "[Select Course]";
        for(int i = 1; i < instructorNames.length; ++i)
            instructorNames[i] = allCoursesList.get(i - 1).getSubject();
        return instructorNames;
    }

    public AdapterView.OnItemSelectedListener courcesSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedCourse = String.valueOf(parent.getItemAtPosition(position));
            courseListAdapter.clear();
            if (selectedCourse.equals("[Select Course]")) {
                for(Professor professor : professorsList)
                    courseListAdapter.add(professor);
            }
            else
            {
                for(Courses course : allCoursesList) {
                    if(course.getCourseName().equals(selectedCourse)) {
                        int instructorID = course.getProfessorId();
                        for (Professor professor : professorsList) {

                        }

                    }
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    */
}
