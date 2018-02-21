package edu.duke.compsci290.quizmaster;

import android.util.Log;

import java.util.List;
import java.util.HashMap;

/**
 * Created by mercyfang on 2/7/18.
 */

public class Question {
    private String mQuestion;
    private List<Answer> mAnswers;
    // mMap maps answer to the attribute associated with it.
    private HashMap<String, String> mMap;
    private String mChosenAnswer;
    private String mDifficulty;

    public Question(String question, List<Answer> answers) {
        mQuestion = question;
        mAnswers = answers;
        mMap = new HashMap<>();
        for (Answer answer : answers) {
            mMap.put(answer.getAnswer(), answer.getAttribute());
        }
    }

    public String getQuestion() {
        return mQuestion;
    }

    public Iterable<Answer> getAnswers() {
        return mAnswers;
    }

    public Answer getAnswer(int index) {
        return mAnswers.get(index);
    }

    public String getAttribute(String answer) {
        if (!mMap.containsKey(answer)) {
            Log.d("Question class", "answer is not found");
            return "";
        }
        return mMap.get(answer);
    }

    public void processChosen(String chosenAnswer) {
        mChosenAnswer = chosenAnswer;
    }

    public String getChosen() {
        return mChosenAnswer;
    }

    public void setDifficulty(String difficulty) {
        mDifficulty = difficulty;
    }

    public String getDifficulty() {
        return mDifficulty;
    }
}
