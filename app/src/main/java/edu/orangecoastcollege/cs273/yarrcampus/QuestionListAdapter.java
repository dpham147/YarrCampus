package edu.orangecoastcollege.cs273.yarrcampus;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 12/12/2016.
 */

public class QuestionListAdapter extends ArrayAdapter<Questions> {

    private Context mContext;
    private List<Questions> mQuestionsList = new ArrayList<>();
    private  int mResourceId;
    private TextView questionTextView;
    private TextView answerTextView;
    private LinearLayout questionLinearLayout;


    public QuestionListAdapter(Context context, int resource, List<Questions> objects) {
        super(context, resource, objects);
        mContext = context;
        mResourceId = resource;
        mQuestionsList = objects;
    }

    /**
     *  Connects all the variables to the appropriate widgets and text files and sets the values from the object
     * into the item on the list.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);
        questionTextView = (TextView) view.findViewById(R.id.questionTextView);
        answerTextView = (TextView) view.findViewById(R.id.answerTextView);
        questionLinearLayout = (LinearLayout) view.findViewById(R.id.questionLinearLayout);

        Questions selectedQuestion = mQuestionsList.get(position);
        questionLinearLayout.setTag(selectedQuestion);

        questionTextView.setText(selectedQuestion.getmQuestion());
        answerTextView.setText(selectedQuestion.getmAnswer());
        return view;
    }
}
