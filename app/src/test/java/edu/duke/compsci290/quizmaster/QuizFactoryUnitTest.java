package edu.duke.compsci290.quizmaster;

import org.junit.Test;

import java.util.HashSet;

import edu.duke.compsci290.quizmaster.quizcomponent.LinearQuiz;
import edu.duke.compsci290.quizmaster.quizcomponent.NonLinearQuiz;
import edu.duke.compsci290.quizmaster.quizcomponent.PersonalityQuiz;
import edu.duke.compsci290.quizmaster.quizcomponent.Question;
import edu.duke.compsci290.quizmaster.quizutil.QuizFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by mercyfang on 3/8/18.
 */

public class QuizFactoryUnitTest {

    @Test
    public void getLinearQuiz() {
        Question[] questions = new Question[0];
        HashSet<String> attributes = new HashSet<>();
        assertTrue(
                QuizFactory.getQuiz(
                        "linear", questions, "linear quiz", attributes)
                        instanceof LinearQuiz);
    }

    @Test
    public void getPersonalityQuiz() {
        Question[] questions = new Question[0];
        HashSet<String> attributes = new HashSet<>();
        assertTrue(
                QuizFactory.getQuiz(
                        "personality", questions, "personality quiz", attributes)
                        instanceof PersonalityQuiz);
    }

    @Test
    public void getNonLinearQuiz() {
        Question[] questions = new Question[0];
        HashSet<String> attributes = new HashSet<>();
        assertTrue(
                QuizFactory.getQuiz(
                        "nonlinear", questions, "nonlinear quiz", attributes)
                        instanceof NonLinearQuiz);
    }
}
