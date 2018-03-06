package edu.duke.compsci290.quizmaster.quizcomponent;

/**
 * Created by mercyfang on 2/17/18.
 */

public class Answer {
    // In the case of LinearQuiz, the attributes could be either 'correct' or 'incorrect'.
    // For PersonalityQuiz, the attributes are defined by the Quiz object.
    private String mAnswer;
    private String mAttribute;

    public Answer(String answer, String attribute) {
        mAnswer = answer;
        mAttribute = attribute;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public String getAttribute() {
        return mAttribute;
    }
}
