package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.MalformedURLException;

public class QuizScreen extends AppCompatActivity {

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

        RecyclerView rv = findViewById(R.id.activity_quiz_screen_recycler_view);
        final QuizAdapter quizAdapter = new QuizAdapter(this,
                quiz.getQuestions(),
                quiz.getQuestion(0).getChoices(),
                quiz.getQuestion(1).getChoices(),
                quiz.getQuestion(2).getChoices(),
                quiz.getQuestion(3).getChoices(),
                quiz.getQuestion(4).getChoices());
        rv.setAdapter(quizAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        final Button button = findViewById(R.id.submit_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra(
                        getApplicationContext().getString(R.string.scorekey),
                        quizAdapter.computeScore());
                getApplicationContext().startActivity(intent);
            }
        });
    }
}
