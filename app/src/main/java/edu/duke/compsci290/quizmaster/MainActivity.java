package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] quizzes = this.getResources().getStringArray(R.array.quiz_names);
        String[] quizTypes = this.getResources().getStringArray(R.array.quiz_types);
        RecyclerView rv = findViewById(R.id.activity_main_recycler_view);
        rv.setAdapter(new QuizAdapter(this, quizzes, quizTypes));
        rv.setLayoutManager(new LinearLayoutManager(this));


    }
}
