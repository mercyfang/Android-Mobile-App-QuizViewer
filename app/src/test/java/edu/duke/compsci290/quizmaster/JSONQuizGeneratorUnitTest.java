package edu.duke.compsci290.quizmaster;

import org.junit.Test;

import java.net.MalformedURLException;

import edu.duke.compsci290.quizmaster.quizutil.JSONQuizGenerator;

/**
 * Created by mercyfang on 3/9/18.
 */

public class JSONQuizGeneratorUnitTest {
    private int quizID = 0;

    @Test
    public void createGenerator() throws Exception {
        try {
            JSONQuizGenerator generator = new JSONQuizGenerator(quizID);
        } catch (MalformedURLException e) {
            throw new Exception();
        }
    }
}
