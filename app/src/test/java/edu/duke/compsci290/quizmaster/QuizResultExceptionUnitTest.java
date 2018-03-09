package edu.duke.compsci290.quizmaster;

import org.junit.Test;

import edu.duke.compsci290.quizmaster.quizutil.QuizResultException;

import static org.junit.Assert.assertEquals;

/**
 * Created by mercyfang on 3/8/18.
 */

public class QuizResultExceptionUnitTest {
    private String quizResultExceptionString = "Quiz Result Exception";

    @Test
    public void createQuizRescultException() throws Exception {
        QuizResultException quizResultException =
                new QuizResultException(quizResultExceptionString);
        assertEquals(quizResultException.toString(), quizResultExceptionString);
    }
}
