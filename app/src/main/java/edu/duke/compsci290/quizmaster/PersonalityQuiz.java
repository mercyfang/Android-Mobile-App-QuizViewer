package edu.duke.compsci290.quizmaster;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mercyfang on 2/9/18.
 */

public class PersonalityQuiz implements Quiz {
    private ArrayList<Question> mQuestions;
    private String mQuizName;

    public PersonalityQuiz(Question[] questions, String quizName) {
        mQuestions = new ArrayList<>(Arrays.asList(questions));
        mQuizName = quizName;
    }

    public String getQuizName() {
        return mQuizName;
    }

    public ArrayList<Question> getQuestions() {
        return mQuestions;
    }

    public Question getQuestion(int index) {
        return mQuestions.get(index);
    }

    public int getQuestionAmount() {
        return mQuestions.size();
    }
}
