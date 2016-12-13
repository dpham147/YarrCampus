package edu.orangecoastcollege.cs273.yarrcampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FaqActivity extends AppCompatActivity {

    private ListView questionsListView;
    private List<Questions> questionsList;
    private ListAdapter questionsListAdapter;

    /**
     * Creates the activity_FAQ
     * Creates and populates a list of questions.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        questionsList = new ArrayList<>();

        questionsList.add(new Questions("What is the holiday schedule?",
                "Campus is closed during the following dates: April 1, 2016"));
        questionsList.add(new Questions("Where can I find my class schedule?",
                "In your OCC account under web schedule,\n" +
                        " there you will find Building names, Professor names and the CRN"));
        questionsList.add(new Questions("Where can I find parking?",
                "Please download OCC parking application"));
        questionsList.add(new Questions("Can my class schedule change?",
                "Your class schedule is prepared months in advance and changes inevitably occur."));

        questionsListView = (ListView) findViewById(R.id.questionsListView);
        questionsListAdapter =
                new QuestionListAdapter(this, R.layout.question_list_item, questionsList);
        questionsListView.setAdapter(questionsListAdapter);

    }
}
