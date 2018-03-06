package edu.duke.compsci290.quizmaster;

import org.junit.Before;
import org.junit.Test;

import edu.duke.compsci290.quizmaster.quizcomponent.Answer;

import static org.junit.Assert.assertEquals;

/**
 * Created by barbaraxiong on 3/2/18.
 */

public class AnswerUnitTest {
    private String mAnswer1 = "answer1";
    private String mAttribute1 = "attribute1";
    private Answer mAnswer;

    @Before
    public void setUp() {
        mAnswer = new Answer(mAnswer1, mAttribute1);
    }

    @Test
    public void getAnswer() throws Exception {
        assertEquals(mAnswer.getAnswer(), mAnswer1);
    }

    @Test
    public void getAttribute() throws Exception {
        assertEquals(mAnswer.getAttribute(), mAttribute1);
    }
}
