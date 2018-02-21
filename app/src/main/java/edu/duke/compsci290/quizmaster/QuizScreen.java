package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;

public class QuizScreen extends AppCompatActivity {
    private Button mNextButton;
    private Quiz mQuiz;
    private String mQuizName;
    private TextView mQuestionView;
    private int mQuestionIndex;
    private int mScore;
    private String mQuizType;
    private JSONQuizGenerator mJSONQuizGenerator;
    private RadioGroup mChoices;
    private TextView mChoice1;
    private TextView mChoice2;
    private TextView mChoice3;
    private TextView mChoice4;
    private TextView mChoice5;

    private static String INDEX = "INDEX";
    private static String SCORE = "SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        Intent receivedIntent = this.getIntent();
        mQuizType = String.valueOf(receivedIntent.getStringExtra(getString(R.string.quiz_type)));

        try {
            if (mQuizType.equals("linear")) {
                mJSONQuizGenerator = new JSONQuizGenerator(R.string.quiz_1);
            } else if (mQuizType.equals("personality")) {
                mJSONQuizGenerator = new JSONQuizGenerator(R.string.personality_quiz_1);
            } else if (mQuizType.equals("nonlinear")) {
                mJSONQuizGenerator = new JSONQuizGenerator(R.string.non_linear_quiz_1);
            }
        } catch (MalformedURLException e) {
            Log.d("APPMAIN","could not create JSON quiz");
            e.printStackTrace();
        }

        String json = mJSONQuizGenerator.getJSON(this);

        mQuiz = JSONParser.parse(json, mQuizType);
        mQuizName = mQuiz.getQuizName();
        mQuestionView = this.findViewById(R.id.question_text_view);
        mNextButton = this.findViewById(R.id.next_button);
        mQuestionIndex = 0;
        mScore = 0;
        mChoices = this.findViewById(R.id.choices_radio_group);
        mChoice1 = this.findViewById(R.id.choice1_radio_button);
        mChoice2 = this.findViewById(R.id.choice2_radio_button);
        mChoice3 = this.findViewById(R.id.choice3_radio_button);
        mChoice4 = this.findViewById(R.id.choice4_radio_button);
        mChoice5 = this.findViewById(R.id.choice5_radio_button);

        askQuestion();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextQuestion();
            }
        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mQuestionIndex = savedInstanceState.getInt(INDEX);
        mScore = savedInstanceState.getInt(SCORE);
        askQuestion();
        // Restores mQuestionIndex to the current index.
        mQuestionIndex--;
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        state.putInt(INDEX, mQuestionIndex);
        state.putInt(SCORE, mScore);
        super.onSaveInstanceState(state);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void nextQuestion() {
        updateScoreAndToastMessage();
        mChoices.clearCheck();
        if (mQuestionIndex < mQuiz.getQuestionAmount() - 1) {
            mQuestionIndex++;
            askQuestion();
        } else {
            // In case of last question, show the result view.
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            switch (mQuizType) {
                // Linear Quiz result page displays the score.
                case "linear": case "nonlinear":
                    intent.putExtra(getApplicationContext().getString(R.string.scorekey),
                            Integer.toString(mScore) + " out of "
                                    + Integer.toString(mQuiz.getQuestionAmount()));
                    break;
                // Personality Quiz result page displays the most accurate personality attribute.
                case "personality":
                    String processedResult = "";
                    try {
                        processedResult = mQuiz.processResult();
                    } catch (QuizResultException e) {
                        processedResult = "No Result";
                    }
                    intent.putExtra(getApplicationContext().getString(R.string.scorekey),
                            processedResult);
                    break;
                default:
                    break;
            }
            intent.putExtra(
                    getApplicationContext().getString(R.string.quiz_name),
                    mQuizName);
            getApplicationContext().startActivity(intent);
        }
    }

    private void updateScoreAndToastMessage() {
        int checkedRadioButtonId = mChoices.getCheckedRadioButtonId();
        String selectedButtonText = "";
        if (checkedRadioButtonId != -1) {
            RadioButton selectedButton = mChoices.findViewById(checkedRadioButtonId);
            selectedButtonText = selectedButton.getText().toString();
        }
        switch (mQuizType) {
            case "linear": case "nonlinear":
                mQuiz.getQuestion(mQuestionIndex).processChosen(selectedButtonText);
                if (mQuiz.getQuestion(mQuestionIndex).getAttribute(selectedButtonText)
                        .equals("correct")) {
                    mScore++;
                    Toast.makeText(QuizScreen.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizScreen.this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
                break;
            case "personality":
                mQuiz.getQuestion(mQuestionIndex).processChosen(selectedButtonText);
                break;
            default:
                Log.d("QuizScreen", "quiz type is not specified");
                break;
        }
    }

    private void askQuestion() {
        Question q = mQuiz.getQuestion(mQuestionIndex);
        mQuestionView.setText(q.getQuestion());
        mChoice1.setText(q.getAnswer(0).getAnswer());
        mChoice2.setText(q.getAnswer(1).getAnswer());
        mChoice3.setText(q.getAnswer(2).getAnswer());
        mChoice4.setText(q.getAnswer(3).getAnswer());
        mChoice5.setText(q.getAnswer(4).getAnswer());
    }
}
