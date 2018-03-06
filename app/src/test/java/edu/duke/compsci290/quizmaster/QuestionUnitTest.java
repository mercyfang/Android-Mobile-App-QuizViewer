package edu.duke.compsci290.quizmaster;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.duke.compsci290.quizmaster.quizcomponent.Answer;
import edu.duke.compsci290.quizmaster.quizcomponent.Question;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * Created by mercyfang on 2/24/18.
 */

public class QuestionUnitTest {
    private Question mQuestion;
    private String mAnswer1 = "answer 1";
    private String mAttribute1 = "correct";
    private String mAnswer2 = "answer 2";
    private String mAttribute2 = "incorrect";
    private String mQuest = "Test Question 1";
    private String mDifficulty = "easy";
    private Iterable<Answer> mAnswers;

    @Before
    public void setUp() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(mAnswer1, mAttribute1));
        answers.add(new Answer(mAnswer2, mAttribute2));
        mAnswers = answers;
        mQuestion = new Question(mQuest, answers);
    }
    @Test
    public void getQuestion() throws Exception {
        assertEquals(mQuestion.getQuestion(), mQuest);
    }

    @Test
    public void getAnswer() throws Exception {
        assertSame(mQuestion.getAnswers(), mAnswers);
    }

    @Test
    public void getAttribute() throws Exception {
        assertEquals(mQuestion.getAttribute(mAnswer1), mAttribute1);
        assertEquals(mQuestion.getAttribute(mAnswer2), mAttribute2);
    }

    @Test
    public void processChosen() throws Exception {
        mQuestion.processChosen(mAnswer1);
        assertEquals(mQuestion.getChosen(), mAnswer1);
    }

    @Test
    public void setDifficulty() throws Exception {
        mQuestion.setDifficulty(mDifficulty);
        assertEquals(mQuestion.getDifficulty(), mDifficulty);
    }
}
