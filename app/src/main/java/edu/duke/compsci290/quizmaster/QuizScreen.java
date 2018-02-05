package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class QuizScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        String[] questions = this.getResources().getStringArray(R.array.question_list);
        String[] choice1 = this.getResources().getStringArray(R.array.choice1);
        String[] choice2 = this.getResources().getStringArray(R.array.choice2);
        String[] choice3 = this.getResources().getStringArray(R.array.choice3);
        String[] choice4 = this.getResources().getStringArray(R.array.choice4);
        String[] choice5 = this.getResources().getStringArray(R.array.choice5);
        RecyclerView rv = findViewById(R.id.activity_quiz_screen_recycler_view);
        rv.setAdapter(new QuizAdapter(this, questions, choice1, choice2, choice3, choice4, choice5));
        rv.setLayoutManager(new LinearLayoutManager(this));
        final Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });
    }
}
