package edu.orangecoastcollege.cs273.yarrcampus;

/**
 * Created by roman on 12/12/2016.
 */

/**
 * Questions class
 */
public class Questions {
    private String mQuestion;
    private String mAnswer;

    /**
     * Overloaded constructor, sets all the variables to the object.
     * @param question
     * @param answer
     */
    public Questions(String question, String answer)
    {
        mQuestion = question;
        mAnswer = answer;
    }

    /**
     * returns question
     * @return
     */
    public String getmQuestion() {
        return mQuestion;
    }

    /**
     * sets the questions
     * @param mQuestion
     */
    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    /**
     * returns the answer
     * @return
     */
    public String getmAnswer() {
        return mAnswer;
    }

    /**
     * sets the answer
     * @param mAnswer
     */
    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }
}
