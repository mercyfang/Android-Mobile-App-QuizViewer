package edu.duke.compsci290.quizmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class QuizScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        String[] questions = this.getResources().getStringArray(R.array.question_list);
        RecyclerView rv = findViewById(R.id.activity_quiz_screen_recycler_view);
        rv.setAdapter(new QuizAdapter(this, questions));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
