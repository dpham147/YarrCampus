package edu.orangecoastcollege.cs273.yarrcampus;

/**
 * Created by roman on 12/12/2016.
 */

public class Questions {
    private String mQuestion;
    private String mAnswer;

    public Questions(String question, String answer)
    {
        mQuestion = question;
        mAnswer = answer;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }
}
