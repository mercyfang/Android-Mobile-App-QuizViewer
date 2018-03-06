package edu.duke.compsci290.quizmaster;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by barbaraxiong on 3/2/18.
 */

public class PersonalityQuizUnitTest {
    private PersonalityQuiz mPersonalityQuiz;
    private ArrayList<Question> mQuestions;
    private Question mQuestion;
    private Iterable<Answer> mAnswers;
    private String mAnswer1 = "answer 1";
    private String mAttribute1 = "correct";
    private String mAnswer2 = "answer 2";
    private String mAttribute2 = "incorrect";
    private String mQuest = "Test Question 1";
    private String mQuizName = "quiz name";
    private int mCurrentQuestion;
    private int mScore;
    private HashSet<String> mAttributes;

    @Before
    public void setup() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(mAnswer1, mAttribute1));
        answers.add(new Answer(mAnswer2, mAttribute2));
        mAnswers = answers;
        mQuestion = new Question(mQuest, answers);
        mQuestions = new ArrayList<Question>();
        mQuestions.add(mQuestion);
        mCurrentQuestion = 0;
        mScore = 0;
        mAttributes = new HashSet<String>();
        mAttributes.add(mAttribute1);
        mAttributes.add(mAttribute2);
        mPersonalityQuiz = new PersonalityQuiz(
                mQuestions.toArray(new Question[mQuestions.size()]), mQuizName, mAttributes);
    }

    @Test
    public void updateCurrentQuestionIndex() {
        mPersonalityQuiz.updateCurrentQuestionIndex(0);
        assertEquals(mCurrentQuestion, 0);
        mPersonalityQuiz.updateCurrentQuestionIndex(mQuestions.size()-1);
        assertEquals(mCurrentQuestion, mQuestions.size()-1);
    }

    @Test
    public void getCurrentQuestionIndex() throws Exception{
        assertEquals(mPersonalityQuiz.getCurrentQuestionIndex(), mCurrentQuestion);
    }

    @Test
    public void getQuizName() throws Exception{
        assertEquals(mPersonalityQuiz.getQuizName(), mQuizName);
    }

    @Test
    public void getQuestions() throws Exception{
        assertEquals(mPersonalityQuiz.getQuestions(), mQuestions);
    }

    @Test
    public void getQuestion() throws Exception {
        assertEquals(mPersonalityQuiz.getQuestion(0), mQuestions.get(0));
        assertEquals(mPersonalityQuiz.getQuestion(mQuestions.size()-1),
                mQuestions.get(mQuestions.size()-1));
    }

    @Test
    public void getQuestionAmount() throws Exception{
        assertEquals(mPersonalityQuiz.getQuestionAmount(), mQuestions.size());
    }
}