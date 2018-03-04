package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        Intent receivedIntent = this.getIntent();
        String quizName = String.valueOf(
                receivedIntent.getStringExtra(getString(R.string.quiz_name)));
        String quizScore = String.valueOf(
                receivedIntent.getStringExtra(getString(R.string.scorekey)));

        TextView heading = findViewById(R.id.heading_text_view);
        TextView score = findViewById(R.id.score_text_view);
        heading.setText(quizName);
        score.setText(quizScore);
    }

    @Override
    public void onPause() {
        // Removes stored quiz name and type information so upon restarting the app, MainActivity
        // should be shown.
        SharedPreferences prefs = getSharedPreferences("QuizScreen", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("quiz_name_key");
        editor.remove("quiz_type_key");
        editor.commit();
        super.onPause();
    }
}
