package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class ProfessorListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Professor> professorsList;

    private EditText searchProfessorEditText;
    private ListView professorListView;
    private List<Professor> filteredProfessors;

    private ProfessorListAdapter professorListAdapter;

    /**
     * Creates activity_professor_list and sets each variable to its appropriate widgets and text fields
     * as well as setting any Listeners to the variables.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_list);

        db = new DBHelper(this);
        db.deleteAllProfessors();

        // db.importProfessors("professors.csv")
        /**
         * Hard Coding Professors in professorList
         */
        Uri image = getUriResource(this, R.drawable.empty_profile_pic);
        Professor professor = new Professor("Michael", "Super Hacker", "12:00pm to 3:00pm","MBCC"
                ,image, 33.671404f, -117.911482f, "professor1@gmail.com", "714-445-2997");
        db.addProfessor(professor );
        Log.i("YarrCampus", "Professor id: " + professor.getmId() );
        db.addProfessor(new Professor("Michael 2.0", "Faster...Stronger...", "12:00pm to 3:00pm","MBCC" ,
                image,33.669912f, -117.911927f, "professor2@gmail.com", "715-445-2997"));

        db.addProfessor(new Professor("Art Moore", "Interesting guy", "1700-2000", "MBCC",
                image, 33.671380f, -117.911276f, "professor3@gmail.com", "716-445-2997"));
        db.addProfessor(new Professor("Arlene Vieau", "Nice lady", "0000-2400", "CHEM",
                image, 33.670817f, -117.912982f, "professor4@gmail.com", "717-445-2997"));


        professorsList = db.getAllProfessors();



        filteredProfessors = new ArrayList<>(professorsList);
        searchProfessorEditText = (EditText) findViewById(R.id.searchProfessorEditText);
        professorListView = (ListView) findViewById(R.id.ProfessorListView);
        professorListAdapter =
                new ProfessorListAdapter(this, R.layout.professor_list_item, filteredProfessors);
        professorListView.setAdapter(professorListAdapter);
        searchProfessorEditText.addTextChangedListener(searchTextChangedListener);


    }

    /**
     * Sets the Search Text to an empty string
     * @param view
     */
    public void reset(View view) {
        searchProfessorEditText.setText("");
    }

    /**
     * Creates an intent to pass the professor to ProfessorDetailsActivity
     * Launches ProfessorDetailsActivity
     * @param view
     */
    public void viewProfessorDetails (View view)
    {
            Intent professorDetailsIntent = new Intent(this, ProfessorDetailsActivity.class);
            Professor selectedProfessor = (Professor) view.getTag();
            professorDetailsIntent.putExtra("SelectedProfessor", selectedProfessor);
            startActivity(professorDetailsIntent);
    }

    /**
     * Listens to text change edit text
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

    /**
     * Get URI to any resource type within  an android studio project static
     * to allow other classes to use  it as a helper function
     * @param context The current context
     * @param resId The resource by given id
     * @return Uri to resource by given id
     * @throws Resources.NotFoundException if the given resource does not exist
     */
    public static Uri getUriResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException
    {
        //Return  a resource instance for your application package
        Resources res = context.getResources();

    //return uri
    return  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
            "://" + res.getResourcePackageName(resId) + '/' + res.getResourceTypeName(resId)
            + '/' + res.getResourceEntryName(resId));

}
}

