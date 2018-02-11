package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;

public class QuizScreen extends AppCompatActivity {

    private Button mNextButton;
    private Quiz quiz;
    private TextView mQuestionView;
    private TextView mScoreView;
    private int mQuestionIndex;
    private int mCorrect;
    private String mScoreBase;
    private boolean mQuizOver = false;
    private JSONQuizGenerator mJSONQuizGenerator;

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
        Quiz quiz = JSONParser.parse(json);
        mQuestionView = this.findViewById(R.id.question_text_view);
        mNextButton = this.findViewById(R.id.next_button);
        mScoreView = this.findViewById(R.id.score_text_view);
        mScoreBase = mScoreView.getText().toString();


        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//                intent.putExtra(
//                        getApplicationContext().getString(R.string.scorekey)),
//                        quizAdapter.computeScore());
                getApplicationContext().startActivity(intent);
            }
        });
    }
}
