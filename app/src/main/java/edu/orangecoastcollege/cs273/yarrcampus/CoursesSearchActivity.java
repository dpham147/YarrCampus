package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
    private ListView coursesListView;

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
        db.addBuilding(new Building(2, "Writers Row", "WRR", "8:00-10:00", Uri.EMPTY, 0.0f, 0.0f));

        db.addProfessor(new Professor(1, "M. Paulding", "Cool guy", "800-2000", "MBSC", Uri.EMPTY, 0.0f, 0.0f));
        db.addProfessor(new Professor(2, "Art Moore", "Interesting guy", "1700-2000", "MBSC", Uri.EMPTY, 0.0f, 0.0f));
        db.addProfessor(new Professor(3, "Eigenvalue", "Simple guy", "0000-2400", "WR", Uri.EMPTY, 0.0f, 0.0f));

        coursesList = db.getAllCourses();
        filteredCoursesList = new ArrayList<>(coursesList);
        professorList = db.getAllProfessors();
        buildingList = db.getAllBuildings();

        professorSpinner = (Spinner) findViewById(R.id.professorSpinner);
        ArrayAdapter<String> professorSpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getProfessorNames());
        professorSpinner.setAdapter(professorSpinnerAdapter);
        professorSpinner.setOnItemSelectedListener(professorSpinnerListener);

        buildingSpinner = (Spinner) findViewById(R.id.buildingSpinner);
        ArrayAdapter<String> buildingSpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getBuildingNames());
        buildingSpinner.setAdapter(buildingSpinnerAdapter);
        buildingSpinner.setOnItemSelectedListener(buildingSpinnerListener);

        crn = (EditText) findViewById(R.id.searchCourseEditText);
        crn.addTextChangedListener(crnTextWatcher);

        coursesListView = (ListView) findViewById(R.id.coursesListView);
        coursesListAdapter = new CoursesListAdapter(this, R.layout.courses_list_item, filteredCoursesList);
        coursesListView.setAdapter(coursesListAdapter);

        logList();


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

    private String[] getBuildingNames()
    {
        String[] buildingNames = new String[buildingList.size() + 1];
        buildingNames[0] = "[Select Building]";
        for (int i = 1; i < buildingNames.length; i++)
        {
            buildingNames[i] = buildingList.get(i-1).getName();
        }
        return buildingNames;
    }


    public void viewCourseDetails (View view)
    {
        Intent courseDetails = new Intent(this, CourseDetailsActivity.class);
        Courses course = (Courses) view.getTag();
        courseDetails.putExtra("Course", course);
        startActivity(courseDetails);
    }

    public void reset(View view) {
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
//            String input = charSequence.toString();
//            String professorName = String.valueOf(professorSpinner.getSelectedItem());
//            String buildingName = String.valueOf(buildingSpinner.getSelectedItem());
//            coursesListAdapter.clear();
//            filteredCoursesList.clear();
//
//            if (charSequence.toString().equals(""))
//            {
//                if (professorName.equals("[Select Professor]"))
//                {
//                    if (buildingName.equals("[Select Building]"))
//                    {
//                        filteredCoursesList = coursesList;
//                        coursesListAdapter.notifyDataSetChanged();
//                    }
//                    else
//                    {
//                        for (Courses course : coursesList)
//                        {
//                            if (course.getmBuilding().getName().equals(buildingName) && course.getmProfessor().getmName().equals(professorName))
//                            {
//                                coursesListAdapter.add(course);
//                            }
//                        }
//                    }
//                }
//                else
//                {
//                    if (buildingName.equals("[Select Building]"))
//                    {
//                        for (Courses course : coursesList)
//                        {
//                            if (course.getmProfessor().equals(professorName))
//                            {
//                                coursesListAdapter.add(course);
//                            }
//                        }
//                    }
//                    else
//                    {
//                        for (Courses course : coursesList)
//                        {
//                            if (course.getmBuilding().getName().equals(buildingName) && course.getmProfessor().getmName().equals(professorName))
//                            {
//                                coursesListAdapter.add(course);
//                            }
//                        }
//                    }
//                }
//            }
//            else if (!charSequence.toString().isEmpty())
//            {
//                if (professorName.equals("[Select Professor]"))
//                {
//                    if (buildingName.equals("[Select Building]"))
//                    {
//                        for (Courses course : coursesList)
//                        {
//                            if (input.startsWith(String.valueOf(course.getCRN())))
//                            {
//                                coursesListAdapter.add(course);
//                            }
//                        }
//                    }
//                    else
//                    {
//                        for (Courses course : coursesList)
//                        {
//                            if (course.getmBuilding().getName().equals(buildingName) && course.getmProfessor().getmName().equals(professorName))
//                            {
//                                if (input.startsWith(String.valueOf(course.getCRN())))
//                                {
//                                    coursesListAdapter.add(course);
//                                }
//                            }
//                        }
//                    }
//                }
//                else
//                {
//                    if (buildingName.equals("[Select Building]"))
//                    {
//                        for (Courses course : coursesList)
//                        {
//                            if (course.getmProfessor().equals(professorName))
//                            {
//                                if (input.startsWith(String.valueOf(course.getCRN())))
//                                {
//                                    coursesListAdapter.add(course);
//                                }
//                            }
//                        }
//                    }
//                    else
//                    {
//                        for (Courses course : coursesList)
//                        {
//                            if (course.getmBuilding().getName().equals(buildingName) && course.getmProfessor().getmName().equals(professorName))
//                            {
//                                if (input.startsWith(String.valueOf(course.getCRN())))
//                                {
//                                    coursesListAdapter.add(course);
//                                }
//                            }
//                        }
//                    }
//                }
//            }

            onFilterChanged();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public AdapterView.OnItemSelectedListener buildingSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            onFilterChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    public AdapterView.OnItemSelectedListener professorSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            onFilterChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    public void onFilterChanged()
    {
        String building = String.valueOf(buildingSpinner.getSelectedItem());
        String professor = String.valueOf(professorSpinner.getSelectedItem());
        String crnNumber = crn.getText().toString();

        coursesListAdapter.clear();
        filteredCoursesList.clear();

        if (crnNumber.equals(""))
        {
            if (professor.equals("[Select Professor]"))
            {
                if (building.equals("[Select Building]"))
                {
                    filteredCoursesList = coursesList;
                    coursesListAdapter.notifyDataSetChanged();
                }
                else
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmBuilding().getName().equals(building) && course.getmProfessor().getmName().equals(professor))
                        {
                            coursesListAdapter.add(course);
                        }
                    }
                }
            }
            else
            {
                if (building.equals("[Select Building]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmProfessor().getmName().equals(professor))
                        {
                            coursesListAdapter.add(course);
                        }
                    }
                }
                else
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmBuilding().getName().equals(building) && course.getmProfessor().getmName().equals(professor))
                        {
                            coursesListAdapter.add(course);
                        }
                    }
                }
            }
        }
        else if (!crnNumber.isEmpty())
        {
            if (professor.equals("[Select Professor]"))
            {
                if (building.equals("[Select Building]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (String.valueOf(course.getCRN()).startsWith(crnNumber))
                        {
                            coursesListAdapter.add(course);
                        }
                    }
                }
                else
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmBuilding().getName().equals(building) && course.getmProfessor().getmName().equals(professor))
                        {
                            if (String.valueOf(course.getCRN()).startsWith(crnNumber))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                }
            }
            else
            {
                if (building.equals("[Select Building]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmProfessor().getmName().equals(professor))
                        {
                            if (String.valueOf(course.getCRN()).startsWith(crnNumber))
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
                        if (course.getmBuilding().getName().equals(building) && course.getmProfessor().getmName().equals(professor))
                        {
                            if (String.valueOf(course.getCRN()).startsWith(crnNumber))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                }
            }
        }
    }

    private void logList() {
        for (Courses courses : coursesList)
        {
            Log.i("YarrCampus", courses.toString());
        }
    }

}


