package edu.duke.compsci290.quizmaster;

import java.util.List;
import java.util.HashMap;

/**
 * Created by mercyfang on 2/7/18.
 */

public class Question {
    private String mQuestion;
    private List<Answer> mAnswers;
    private HashMap<String, String> mMap;

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
            return "";
        }
        return mMap.get(answer);
    }
}
