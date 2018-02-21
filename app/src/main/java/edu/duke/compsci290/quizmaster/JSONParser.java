package edu.duke.compsci290.quizmaster;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

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
            HashSet<String> attributes = new HashSet<>();
            for (int i = 0; i < jsonQuestions.length(); i++) {
                JSONObject current = jsonQuestions.getJSONObject(i);
                String question = current.getString("question");
                JSONArray jsonAnswers = current.getJSONArray("answers");
                JSONArray jsonAttributes = current.getJSONArray("attributes");
                ArrayList<Answer> answers = new ArrayList<>();
                for (int j = 0; j < jsonAnswers.length(); j++) {
                    answers.add(new Answer(jsonAnswers.getString(j), jsonAttributes.getString(j)));
                    attributes.add(jsonAttributes.getString(j));
                }
                questions[i] = new Question(question, answers);
            }

            QuizFactory quizFactory = new QuizFactory();
            return quizFactory.getQuiz(quizType, questions, quizName, attributes);
        } catch (JSONException e) {
            Log.d("json parse", "error in parsing");
            e.printStackTrace();
        }
        return null;
    }
}
