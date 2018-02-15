package edu.duke.compsci290.quizmaster;

import java.util.ArrayList;

/**
 * Created by mercyfang on 2/9/18.
 */

public interface Quiz {
    String getQuizName();
    ArrayList<Question> getQuestions();
    Question getQuestion(int index);
    int getQuestionAmount();
}
