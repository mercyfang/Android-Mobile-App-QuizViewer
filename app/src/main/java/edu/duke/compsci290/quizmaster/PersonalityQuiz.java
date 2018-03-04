package edu.duke.compsci290.quizmaster;

import android.annotation.TargetApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mercyfang on 2/9/18.
 */

public class PersonalityQuiz implements Quiz {
    private ArrayList<Question> mQuestions;
    private String mQuizName;
    private int mCurrentQuestion;
    private HashSet<String> mAttributes;

    public PersonalityQuiz(Question[] questions, String quizName, HashSet<String> attributes) {
        mQuestions = new ArrayList<>(Arrays.asList(questions));
        mQuizName = quizName;
        mAttributes = attributes;
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
    }

    @TargetApi(24)
    public String processResult() throws QuizResultException {
        try {
            Map<String, Integer> attributeCount = new HashMap<>();
            for (Question question : mQuestions) {
                String attribute = question.getAttribute(question.getChosen());
                if (!attribute.equals("")) {
                    attributeCount.put(
                            attribute, attributeCount.getOrDefault(attribute, 0) + 1);
                }
            }
            // Sorts the attributes map by count.
            List<String> sortedAttributeCount = attributeCount.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue((Integer i1, Integer i2) -> i2 - i1))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            return sortedAttributeCount.get(0);
        } catch (Exception e) {
            // If user didn't select any choices for any questions, quiz result cannot be processed.
            throw new QuizResultException("Quiz result cannot be processed for Personality Quiz.");
        }
    }
}
