package edu.duke.compsci290.quizmaster.quizcomponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import edu.duke.compsci290.quizmaster.quizutil.QuizResultException;

/**
 * Created by mercyfang on 2/7/18.
 */

public class LinearQuiz implements Quiz {
    private ArrayList<Question> mQuestions;
    private String mQuizName;
    private int mCurrentQuestion;
    private int mScore;

    public LinearQuiz(Question[] questions, String quizName, HashSet<String> attributes) {
        mQuestions = new ArrayList<>(Arrays.asList(questions));
        mQuizName = quizName;
        mCurrentQuestion = 0;
        mScore = 0;
    }

    public void updateCurrentQuestionIndex(int index) {
        mCurrentQuestion = index;
    }

    public int getCurrentQuestionIndex() {
        return mCurrentQuestion;
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

    public void updateScore() {
        mScore++;
    }

    public String processResult() throws QuizResultException {
        try {
            return String.valueOf(mScore);
        } catch (Exception e) {
            throw new QuizResultException("Quiz result cannot be processed for Linear Quiz.");
        }
    }
}
