package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button linearQuizButton = findViewById(R.id.linear_quiz_button);
        final Button personalityQuizButton = findViewById(R.id.personality_quiz_button);
        final Button nonLinearQuizButton = findViewById(R.id.non_linear_quiz_button);
        final Button newQuizButton = findViewById(R.id.new_quiz_button);

        linearQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizScreen.class);
                intent.putExtra(
                        getApplicationContext().getString(R.string.quiz_type),
                        "linear");
                getApplicationContext().startActivity(intent);
            }
        });

        personalityQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizScreen.class);
                intent.putExtra(
                        getApplicationContext().getString(R.string.quiz_type),
                        "personality");
                getApplicationContext().startActivity(intent);
            }
        });

        nonLinearQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizScreen.class);
                intent.putExtra(
                        getApplicationContext().getString(R.string.quiz_type),
                        "nonlinear");
                getApplicationContext().startActivity(intent);
            }
        });

        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: create new quiz defined by user.
            }
        });
    }
}
