package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CoursesSearchActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Courses> coursesList;
    private List<Courses> filteredCoursesList;
    private List<Professor> professorList;
    private List<Building> buildingList;

    private Spinner professorSpinner;
    private Spinner buildingSpinner;
    private EditText crn;

    private CoursesListAdapter coursesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_search);

        db = new DBHelper(this);
        db.deleteAllCourses();
        db.deleteAllBuildings();
        db.deleteAllProfessors();

        db.addCourse(12345, "CS 273", 1, 1, "Mobile Application Development", "54321");
        db.addCourse(19876, "Math 295", 1, 2, "Tensor Space", "12576");
        db.addCourse(15732, "Eng 101", 2, 3, "College Writing", "32451");

        db.addBuilding(new Building(1, "MBSC", "MBSC", "8:00-10:00", Uri.EMPTY, 0.0f, 0.0f));
        db.addBuilding(new Building(1, "Writers Row", "WRR", "8:00-10:00", Uri.EMPTY, 0.0f, 0.0f));

        db.addProfessor(new Professor(1, "M. Paulding", "Cool guy", "800-2000", "MBSC", Uri.EMPTY, 0.0f, 0.0f));
        db.addProfessor(new Professor(2, "Art Moore", "Interesting guy", "1700-2000", "MBSC", Uri.EMPTY, 0.0f, 0.0f));
        db.addProfessor(new Professor(3, "Eigenvalue", "Simple guy", "0000-2400", "WR", Uri.EMPTY, 0.0f, 0.0f));

        coursesList = db.getAllCourses();
        filteredCoursesList = new ArrayList<>(coursesList);
        professorList = db.getAllProfessors();
        buildingList = db.getAllBuildings();

        professorSpinner = (Spinner) findViewById(R.id.professorSpinner);
        ArrayAdapter<String> professorSpinnerAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getProfessorNames());
        professorSpinner.setAdapter(professorSpinnerAdapter);
        professorSpinner.setOnItemSelectedListener(professorSpinnerListener);


    }

    private String[] getProfessorNames()
    {
        String[] professorNames = new String[professorList.size() + 1];
        professorNames[0] = "[Select Professor]";
        for (int i = 1; i < professorNames.length; i++)
        {
            professorNames[i] = professorList.get(i-1).getmName();
        }
        return professorNames;
    }

    public void reset() {
        crn.setText("");
        professorSpinner.setSelection(0);
        buildingSpinner.setSelection(0);
    }

    public TextWatcher crnTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String input = charSequence.toString();
            String professorName = String.valueOf(professorSpinner.getSelectedItem());
            String buildingName = String.valueOf(buildingSpinner.getSelectedItem());
            coursesListAdapter.clear();
            filteredCoursesList.clear();

            if (charSequence.toString().equals(""))
            {
                if (professorName.equals("[Select Professor]"))
                {
                    if (buildingName.equals("[Select Building]"))
                    {
                        filteredCoursesList = coursesList;
                        coursesListAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        for (Courses course : coursesList)
                        {
                            if (course.getmBuilding().getName().equals(buildingName) && course.getmProfessor().getmName().equals(professorName))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                }
                else
                {
                    if (buildingName.equals("[Select Building]"))
                    {
                        for (Courses course : coursesList)
                        {
                            if (course.getmProfessor().equals(professorName))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                    else
                    {
                        for (Courses course : coursesList)
                        {
                            if (course.getmBuilding().getName().equals(buildingName) && course.getmProfessor().getmName().equals(professorName))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                }
            }
            else if (!charSequence.toString().isEmpty())
            {
                if (professorName.equals("[Select Professor]"))
                {
                    if (buildingName.equals("[Select Building]"))
                    {
                        for (Courses course : coursesList)
                        {
                            if (input.startsWith(String.valueOf(course.getCRN())))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                    else
                    {
                        for (Courses course : coursesList)
                        {
                            if (course.getmBuilding().getName().equals(buildingName) && course.getmProfessor().getmName().equals(professorName))
                            {
                                if (input.startsWith(String.valueOf(course.getCRN())))
                                {
                                    coursesListAdapter.add(course);
                                }
                            }
                        }
                    }
                }
                else
                {
                    if (buildingName.equals("[Select Building]"))
                    {
                        for (Courses course : coursesList)
                        {
                            if (course.getmProfessor().equals(professorName))
                            {
                                if (input.startsWith(String.valueOf(course.getCRN())))
                                {
                                    coursesListAdapter.add(course);
                                }
                            }
                        }
                    }
                    else
                    {
                        for (Courses course : coursesList)
                        {
                            if (course.getmBuilding().getName().equals(buildingName) && course.getmProfessor().getmName().equals(professorName))
                            {
                                if (input.startsWith(String.valueOf(course.getCRN())))
                                {
                                    coursesListAdapter.add(course);
                                }
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public AdapterView.OnItemSelectedListener professorSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String selectedProfName = String.valueOf(adapterView.getItemAtPosition(i));
            coursesListAdapter.clear();

            if (selectedProfName.equals("Select Professor]"))
            {
                for (Courses course : coursesList)
                {
                    coursesListAdapter.add(course);
                }
            }
            else
            {
                for (Courses course : coursesList)
                {
                    Professor professor = course.getmProfessor();
                    if (professor.getmName().equals(selectedProfName))
                    {
                        coursesListAdapter.add(course);
                    }
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}


