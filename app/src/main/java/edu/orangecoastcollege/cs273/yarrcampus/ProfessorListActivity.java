package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
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
        db.deleteAllProfessors();

        // db.importProfessors("professors.csv")
        Uri image = getUriResource(this, R.drawable.empty_profile_pic);
        db.addProfessor(new Professor("Michael", "Super Hacker", "12:00pm to 3:00pm","MBCC" ,image));
        db.addProfessor(new Professor("Michael 2.0", "Faster...Stronger...", "12:00pm to 3:00pm","MBCC" ,image));

        professorsList = db.getAllProfessors();

        /**
         * Hard Coding Professors in professorList
         */


        filteredProfessors = new ArrayList<>(professorsList);
        searchProfessorEditText = (EditText) findViewById(R.id.searchProfessorEditText);
        professorListView = (ListView) findViewById(R.id.ProfessorListView);
        professorListAdapter =
                new ProfessorListAdapter(this, R.layout.professor_list_item, filteredProfessors);
        professorListView.setAdapter(professorListAdapter);
        searchProfessorEditText.addTextChangedListener(searchTextChangedListener);


    }

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
