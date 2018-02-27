package edu.duke.compsci290.quizmaster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by mercyfang on 2/27/18.
 */

public class JSONParserUnitTest {
    private String linearQuizType = "linear";
    private String nonLinearQuizType = "nonlinear";
    private String personalityQuizType = "personality";
    private JSONObject question;

    @Before
    public void setUp() throws Exception {
        question = new JSONObject();
        question.put("question", "question test");

        JSONArray answers = new JSONArray();
        answers.put("answer test");
        JSONArray attributes = new JSONArray();
        attributes.put("attribute test");

        question.put("answers", answers);
        question.put("attributes", attributes);
    }

    @Test
    public void parseLinearQuiz() throws Exception {
        JSONObject quizJSONObj = new JSONObject();
        quizJSONObj.put("quizName", "Linear Quiz Test");
        JSONArray questions = new JSONArray();
        questions.put(question);
        quizJSONObj.put("questions", questions);
        String jString = quizJSONObj.toString();

        Quiz quiz = JSONParser.parse(jString, linearQuizType);
        assertTrue(quiz instanceof LinearQuiz);
    }

    @Test
    public void parsePersonalityQuiz() throws Exception {
        JSONObject quizJSONObj = new JSONObject();
        quizJSONObj.put("quizName", "Personality Quiz Test");
        JSONArray questions = new JSONArray();
        questions.put(question);
        quizJSONObj.put("questions", questions);

        Quiz quiz = JSONParser.parse(quizJSONObj.toString(), personalityQuizType);
        assertTrue(quiz instanceof PersonalityQuiz);
    }

    @Test
    public void parseNonLinearQuiz() throws Exception {
        JSONObject quizJSONObj = new JSONObject();
        quizJSONObj.put("quizName", "Linear Quiz Test");
        JSONArray questions = new JSONArray();
        question.put("difficulty", "easy");
        questions.put(question);
        quizJSONObj.put("questions", questions);

        Quiz quiz = JSONParser.parse(quizJSONObj.toString(), nonLinearQuizType);
        assertTrue(quiz instanceof NonLinearQuiz);
    }

}
