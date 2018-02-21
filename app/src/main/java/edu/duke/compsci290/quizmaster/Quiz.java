package edu.duke.compsci290.quizmaster;

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
}
