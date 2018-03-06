package edu.duke.compsci290.quizmaster.quizutil;

/**
 * Created by mercyfang on 2/20/18.
 */

public class QuizResultException extends Exception {
    private final String mMessage;

    public QuizResultException(String message) {
        mMessage = message;
    }

    @Override
    public String toString() {
        return mMessage;
    }
}
