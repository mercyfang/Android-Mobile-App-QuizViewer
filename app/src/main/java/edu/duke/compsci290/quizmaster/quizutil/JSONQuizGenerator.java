package edu.duke.compsci290.quizmaster.quizutil;

import android.content.Context;

import java.net.MalformedURLException;

import edu.duke.compsci290.quizmaster.QuizScreen;

/**
 * Created by mercyfang on 2/7/18.
 */

public class JSONQuizGenerator {
    private int mQuizID;

    public JSONQuizGenerator(int quizID) throws MalformedURLException {
        mQuizID = quizID;
    }

    public String getJSON(final QuizScreen quizScreen) {
        Context c = quizScreen.getApplicationContext();
        return c.getResources().getString(mQuizID);
    }
}
