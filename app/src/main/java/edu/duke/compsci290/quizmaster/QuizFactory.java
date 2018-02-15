package edu.duke.compsci290.quizmaster;

import android.util.Log;

/**
 * Created by mercyfang on 2/11/18.
 */

public class QuizFactory {

    // Uses quiz type to get object of a certain type of quiz.
    public static Quiz getQuiz(String quizType, Question[] questions, String quizName) {
        if (quizType.isEmpty()) {
            Log.d("quiz factory", "quiz type not specified");
            return null;
        }
        if (quizType.equals("linear")) {
            return new LinearQuiz(questions, quizName);
        } else if (quizType.equals("personality")) {
            return new PersonalityQuiz(questions, quizName);
        }
        return null;
    }
}
