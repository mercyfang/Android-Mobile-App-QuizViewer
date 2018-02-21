package edu.duke.compsci290.quizmaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by mercyfang on 2/21/18.
 */

public class NonLinearQuiz implements Quiz {
    private ArrayList<Question> mQuestions;
    private String mQuizName;
    private int mCurrentQuestion;

    public NonLinearQuiz(Question[] questions, String quizName, HashSet<String> attributes) {
        mQuestions = new ArrayList<>(Arrays.asList(questions));
        mQuizName = quizName;
        mCurrentQuestion = 0;
    }

    public void updateCurrentQuestionIndex(int index) {
        mCurrentQuestion = index;
    }

    public String getQuizName() {
        return mQuizName;
    }

    public Iterable<Question> getQuestions() {
        return mQuestions;
    }

    public Question getQuestion(int index) {
        return mQuestions.get(index);
    }

    public int getQuestionAmount() {
        return mQuestions.size();
    }

    public String processResult() throws QuizResultException {
        try {
            return "";
        } catch (Exception e) {
            throw new QuizResultException("Quiz result cannot be processed for Non-Linear Quiz.");
        }
    }
}