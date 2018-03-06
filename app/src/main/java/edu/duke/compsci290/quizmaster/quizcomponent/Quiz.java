package edu.duke.compsci290.quizmaster.quizcomponent;

import edu.duke.compsci290.quizmaster.quizutil.QuizResultException;

/**
 * Created by mercyfang on 2/9/18.
 */

public interface Quiz {
    void updateCurrentQuestionIndex(int index);
    String getQuizName();
    Iterable<Question> getQuestions();
    Question getQuestion(int index);
    int getQuestionAmount();
    String processResult() throws QuizResultException;
    void updateScore();
}
