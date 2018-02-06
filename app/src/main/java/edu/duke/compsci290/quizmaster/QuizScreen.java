package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class QuizScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        String[] questions = this.getResources().getStringArray(R.array.question_list);
        String[] choices1 = this.getResources().getStringArray(R.array.choices1);
        String[] choices2 = this.getResources().getStringArray(R.array.choices2);
        String[] choices3 = this.getResources().getStringArray(R.array.choices3);
        String[] choices4 = this.getResources().getStringArray(R.array.choices4);
        String[] choices5 = this.getResources().getStringArray(R.array.choices5);

        RecyclerView rv = findViewById(R.id.activity_quiz_screen_recycler_view);
        final QuizAdapter quizAdapter = new QuizAdapter(
                this, questions, choices1, choices2, choices3, choices4, choices5);
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
