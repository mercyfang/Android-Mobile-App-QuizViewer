package edu.duke.compsci290.quizmaster.quizcomponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import edu.duke.compsci290.quizmaster.quizutil.QuizResultException;

/**
 * Created by mercyfang on 2/21/18.
 */

public class NonLinearQuiz implements Quiz {
    private ArrayList<Question> mQuestions;
    private String mQuizName;
    private int mCurrentQuestion;
    private int mScore;
    private int mHardQuestionCount;

    private Iterator<Question> mEasyQuestions;
    private Iterator<Question> mHardQuestions;

    public NonLinearQuiz(Question[] questions, String quizName, HashSet<String> attributes) {
        mQuestions = new ArrayList<>(Arrays.asList(questions));
        mQuizName = quizName;
        mCurrentQuestion = 0;
        mScore = 0;
        buildEasyAndHardQuestionIterator();
    }

    // Uses Iterator to hold easy and hard questions.
    private void buildEasyAndHardQuestionIterator() {
        ArrayList<Question> mEasyArrayList = new ArrayList<>();
        ArrayList<Question> mHardArrayList = new ArrayList<>();
        mHardQuestionCount = 0;
        for (int i = 0; i < mQuestions.size(); i++) {
            Question question = mQuestions.get(i);
            if (question.getDifficulty().equals("easy")) {
                mEasyArrayList.add(question);
            } else if (question.getDifficulty().equals("hard")) {
                mHardQuestionCount++;
                mHardArrayList.add(question);
            }
        }
        mEasyQuestions = mEasyArrayList.iterator();
        mHardQuestions = mHardArrayList.iterator();
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

    public void updateScore() {
        mScore++;
    }

    public Iterator<Question> getEasyQuestions() {
        return mEasyQuestions;
    }

    public Iterator<Question> getHardQuestions() {
        return mHardQuestions;
    }

    public int getHardQuestionCount() {
        return mHardQuestionCount;
    }

    public String processResult() throws QuizResultException {
        try {
            return String.valueOf(mScore);
        } catch (Exception e) {
            throw new QuizResultException("Quiz result cannot be processed for Non-Linear Quiz.");
        }
    }
}
