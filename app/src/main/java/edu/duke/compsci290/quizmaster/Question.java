package edu.duke.compsci290.quizmaster;

/**
 * Created by mercyfang on 2/7/18.
 */

public class Question {
    private String mQuestion;
    private String[] mChoices;
    private String mAnswer;

    public Question(String question, String[] choices, String answer) {
        mQuestion = question;
        mAnswer = answer;
        mChoices = choices;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String[] getChoices() {
        return mChoices;
    }

    public String getAnswer() {
        return mAnswer;
    }
}
