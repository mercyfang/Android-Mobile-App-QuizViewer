package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.net.MalformedURLException;

public class QuizScreen extends AppCompatActivity {
    private Button mNextButton;
    private Quiz mQuiz;
    private TextView mQuestionView;
    private int mQuestionIndex;
    private int mScore;
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

        try {
            mJSONQuizGenerator = new JSONQuizGenerator(R.string.quiz_1);
        } catch (MalformedURLException e) {
            Log.d("APPMAIN","could not create JSON quiz");
            e.printStackTrace();
        }
        String json = mJSONQuizGenerator.getJSON(this);
        mQuiz = JSONParser.parse(json);
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
                mChoices.clearCheck();
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
        if (mQuestionIndex < mQuiz.getQuestionAmount() - 1) {
            mQuestionIndex++;
            askQuestion();
        } else {
            // In case of last question, show the result view.
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra(getApplicationContext().getString(R.string.scorekey), mScore);
            getApplicationContext().startActivity(intent);
        }
    }

    private void askQuestion() {
        Question q = mQuiz.getQuestion(mQuestionIndex);
        mQuestionView.setText(q.getQuestion());
        String[] choices = q.getChoices();
        mChoice1.setText(choices[0]);
        mChoice2.setText(choices[1]);
        mChoice3.setText(choices[2]);
        mChoice4.setText(choices[3]);
        mChoice5.setText(choices[4]);
    }
}
