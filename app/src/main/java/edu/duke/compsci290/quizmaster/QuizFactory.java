package edu.duke.compsci290.quizmaster;

import android.util.Log;

import java.util.HashSet;

/**
 * Created by mercyfang on 2/11/18.
 */

public class QuizFactory {

    // Uses quiz type to get object of a certain type of quiz.
    public static Quiz getQuiz(
            String quizType, Question[] questions, String quizName, HashSet<String> attributes) {
        if (quizType.isEmpty()) {
            Log.d("quiz factory", "quiz type not specified");
            return null;
        }
        if (quizType.equals("linear")) {
            return new LinearQuiz(questions, quizName, attributes);
        } else if (quizType.equals("personality")) {
            return new PersonalityQuiz(questions, quizName, attributes);
        }
        return null;
    }
}
