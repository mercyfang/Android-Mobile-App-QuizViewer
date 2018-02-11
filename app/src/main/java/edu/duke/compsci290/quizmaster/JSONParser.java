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
            JSONArray jsonQuestions = all.getJSONArray("questions");
            Question[] questions = new Question[jsonQuestions.length()];
            for (int i = 0; i < jsonQuestions.length(); i++) {
                JSONObject current = jsonQuestions.getJSONObject(i);
                String question = current.getString("question");
                JSONArray jsonChoices = current.getJSONArray("choices");
                String[] choices = new String[jsonChoices.length()];
                for (int j = 0; j < choices.length; j++) {
                    choices[j] = jsonChoices.getString(j);
                }
                String answer = current.getString("answer");
                questions[i] = new Question(question, choices, answer);
            }

            QuizFactory quizFactory = new QuizFactory();
            return quizFactory.getQuiz(quizType, questions);
        } catch (JSONException e) {
            Log.d("json parse", "error in parsing");
            e.printStackTrace();
        }
        return null;
    }
}
