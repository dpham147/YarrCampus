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

import static edu.orangecoastcollege.cs273.yarrcampus.ProfessorListActivity.getUriResource;

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
        deleteDatabase(DBHelper.DATABASE_NAME);

        db.addBuilding(new Building("Watson Hall", "WTNH", "9:00 AM - 6:00 PM", getUriResource(this, R.drawable.basic_building), 33.670686f,  -117.909255f));
        db.addBuilding(new Building("Math Business and Computing Center", "MBCC", "9:00 AM - 10:00 PM", getUriResource(this, R.drawable.basic_building), 33.670797f, -117.912142f));
        db.addBuilding(new Building("Chemistry", "CHEM", "9:00 AM - 6:00 PM", getUriResource(this, R.drawable.basic_building), 33.671648f, -117.914652f));
        db.addBuilding(new Building("Library", "LIBR", "9:00 AM - 10:00 PM", getUriResource(this, R.drawable.basic_building), 33.668890f, -117.912638f));

        Uri image = getUriResource(this, R.drawable.empty_profile_pic);
        db.addProfessor(new Professor("Michael", "Super Hacker", "12:00pm to 3:00pm","MBCC" , getUriResource(this, R.drawable.mpaulding), 33.671404f, -117.911482f));
        db.addProfessor(new Professor("Michael 2.0", "Faster...Stronger...", "12:00pm to 3:00pm","MBCC" , getUriResource(this, R.drawable.mpaulding), 33.671404f, -117.911482f));
        db.addProfessor(new Professor("Art Moore", "Interesting guy", "1700-2000", "MBCC", image, 0.0f, 0.0f));
        db.addProfessor(new Professor("Eigenvalue", "Simple guy", "0000-2400", "WR", image, 0.0f, 0.0f));
        db.addProfessor(new Professor("Arlene Vieau", "Nice lady", "0000-2400", "CHEM", image, 0.0f, 0.0f));
        db.addProfessor(new Professor("Gilbert", "Cool guy", "0000-2400", "MBCC", image, 0.0f, 0.0f));

        db.addCourse(12356, "Eng 100", 1, 4, "English", "52345");
        db.addCourse(12367, "Eng 101", 1, 4, "College Writing", "52245");
        db.addCourse(23467, "CS 150", 2, 1, "C++ Programming 1", "36451");
        db.addCourse(23578, "CS 170", 2, 6, "Java Programming 1", "36342");
        db.addCourse(23590, "CS 250", 2, 2, "C++ Programming 2", "25723");
        db.addCourse(23932, "CS 273", 2, 1, "Mobile Application Development", "54321");
        db.addCourse(31252, "Math 235", 2, 3, "Linear Algebra and Differential Equations", "34573");
        db.addCourse(32156, "Math 280", 2, 3, "Calculus 3", "23654");
        db.addCourse(35216, "Math 295", 2, 3, "Tensor Space", "12576");
        db.addCourse(42692, "Chem 180", 3, 5, "General Chemistry", "34562");
        db.addCourse(46292, "Chem 120", 3, 5, "Prep for General Chemistry", "34654");


        professorList = db.getAllProfessors();
        buildingList = db.getAllBuildings();
        coursesList = db.getAllCourses();
        filteredCoursesList = new ArrayList<>(coursesList);


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

        coursesListAdapter = new CoursesListAdapter(this, R.layout.courses_list_item, filteredCoursesList);
        coursesListView = (ListView) findViewById(R.id.coursesListView);
        coursesListView.setAdapter(coursesListAdapter);
    }


    /**
     * Obtains a list of professor names
     * @return A String Array of names
     */
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

    /**
     * Obtains a list of building names
     * @return - A String Array of names
     */
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


    /**
     * Launches CourseDetailsActivity
     * @param view - button
     */
    public void viewCourseDetails (View view)
    {
        Intent courseDetails = new Intent(this, CourseDetailsActivity.class);
        Courses course = (Courses) view.getTag();
        courseDetails.putExtra("Course", course);
        startActivity(courseDetails);
    }

    /**
     * Resets the filters
     * @param view - button
     */
    public void reset(View view) {
        crn.setText("");
        professorSpinner.setSelection(0);
        buildingSpinner.setSelection(0);
    }

    /**
     * TextWatcher for CRN edit text
     */
    public TextWatcher crnTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            onFilterChanged();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    /**
     * Spinner item selected listener
     */
    public AdapterView.OnItemSelectedListener buildingSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           onFilterChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    /**
     * Spinner item selected listener
     */
    public AdapterView.OnItemSelectedListener professorSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            onFilterChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    /**
     * Handles all filter changes
     */
    public void onFilterChanged()
    {
        String building = String.valueOf(buildingSpinner.getSelectedItem());
        String professor = String.valueOf(professorSpinner.getSelectedItem());
        String crnNumber = crn.getText().toString();

        coursesListAdapter.clear();

        if (!crnNumber.isEmpty())
        {
            if (building.equals("[Select Building]"))
            {
                if (professor.equals("[Select Professor]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getCRNString().startsWith(crnNumber))
                        {
                            coursesListAdapter.add(course);
                        }
                    }
                }
                else if (!professor.equals("[Select Professor]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmProfessor().getmName().equals(professor))
                        {
                            if (course.getCRNString().startsWith(crnNumber))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                }
            }
            else if (!building.equals("[Select Building]"))
            {
                if (professor.equals("[Select Professor]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmBuilding().getName().equals(building))
                        {
                            if (course.getCRNString().startsWith(crnNumber))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                }
                else if (!professor.equals("[Select Professor]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmBuilding().getName().equals(building) && course.getmProfessor().getmName().equals(professor))
                        {
                            if (course.getCRNString().startsWith(crnNumber))
                            {
                                coursesListAdapter.add(course);
                            }
                        }
                    }
                }
            }
        }
        else if (crnNumber.isEmpty())
        {
            if (building.equals("[Select Building]"))
            {
                if (professor.equals("[Select Professor]"))
                {
                    for (Courses course : coursesList)
                    {
                        coursesListAdapter.add(course);
                    }
                }
                else if (!professor.equals("[Select Professor]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmProfessor().getmName().equals(professor))
                        {
                            coursesListAdapter.add(course);
                        }
                    }
                }
            }
            else if (!building.equals("[Select Building]"))
            {
                if (professor.equals("[Select Professor]"))
                {
                    for (Courses course : coursesList)
                    {
                        if (course.getmBuilding().getName().equals(building))
                        {
                            coursesListAdapter.add(course);
                        }
                    }
                }
                else if (!professor.equals("[Select Professor]"))
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


        for (Courses courses : filteredCoursesList)
        {
            Log.i("YarrCampus", "Filtering: " + courses.toString());
        }
    }
}




