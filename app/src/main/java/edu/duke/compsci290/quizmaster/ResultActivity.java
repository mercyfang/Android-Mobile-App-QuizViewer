package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent receivedIntent = this.getIntent();
        String quizScore = String.valueOf(
                receivedIntent.getDoubleExtra(getString(R.string.scorekey), 0.0));

        TextView heading = findViewById(R.id.heading_text_view);
        TextView score = findViewById(R.id.score_text_view);
        heading.setText("Your Score is...");
        score.setText(quizScore);
    }
}
