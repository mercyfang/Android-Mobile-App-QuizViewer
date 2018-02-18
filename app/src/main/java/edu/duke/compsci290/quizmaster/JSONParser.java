package edu.duke.compsci290.quizmaster;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mercyfang on 2/7/18.
 */

public class JSONParser {
    // Quiz parser parses a certain type of quiz using QuizFactory.
    public static Quiz parse(String jString, String quizType) {
        try {
            JSONObject all = new JSONObject(jString);
            String quizName = all.getString("quizName");
            JSONArray jsonQuestions = all.getJSONArray("questions");
            Question[] questions = new Question[jsonQuestions.length()];
            for (int i = 0; i < jsonQuestions.length(); i++) {
                JSONObject current = jsonQuestions.getJSONObject(i);
                String question = current.getString("question");
                JSONArray jsonAnswers = current.getJSONArray("answers");
                JSONArray jsonAttributes = current.getJSONArray("attributes");
                Answer[] answers = new Answer[jsonAnswers.length()];
                for (int j = 0; j < answers.length; j++) {
                    answers[j] = new Answer(jsonAnswers.getString(j), jsonAttributes.getString(j));
                }
                questions[i] = new Question(question, answers);
            }

            QuizFactory quizFactory = new QuizFactory();
            return quizFactory.getQuiz(quizType, questions, quizName);
        } catch (JSONException e) {
            Log.d("json parse", "error in parsing");
            e.printStackTrace();
        }
        return null;
    }

}
