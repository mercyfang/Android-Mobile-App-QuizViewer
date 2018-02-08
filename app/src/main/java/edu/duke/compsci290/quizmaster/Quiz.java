package edu.duke.compsci290.quizmaster;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mercyfang on 2/7/18.
 */

public class Quiz {
    private ArrayList<Question> mQuestions;

    public Quiz(Question[] questions) {
        mQuestions = new ArrayList<>(Arrays.asList(questions));
    }

    public ArrayList<Question> getQuestions() {
        return mQuestions;
    }

    public Question getQuestion(int index) {
        return mQuestions.get(index);
    }
}
