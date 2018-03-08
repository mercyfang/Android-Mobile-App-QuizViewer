package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets mainActivity to enable QuizAdapter access the ApplicationContext.
        mainActivity = this;

        // Restores state for QuizScreen if present.
        SharedPreferences prefs = getSharedPreferences("QuizScreen", MODE_PRIVATE);
        String quizName = prefs.getString("quiz_name_key", "");
        String quizType = prefs.getString("quiz_type_key", "");
        String quizzesCompletion = prefs.getString("quiz_completion", "");
        if (!quizName.isEmpty() && !quizType.isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), QuizScreen.class);
            intent.putExtra("quiz_name_key", quizName);
            intent.putExtra("quiz_type_key", quizType);
            getApplicationContext().startActivity(intent);
            return;
        }
        // Fails to get SharedPreferences, create main activity.
        setContentView(R.layout.activity_main);

        String[] quizzes = this.getResources().getStringArray(R.array.quiz_names);
        String[] quizTypes = this.getResources().getStringArray(R.array.quiz_types);

        if (quizzesCompletion.isEmpty()) {
            for (int i = 0; i < quizzes.length; i++) {
                quizzesCompletion += "-1;";
            }
            quizzesCompletion = quizzesCompletion.substring(0, quizzesCompletion.length() - 1);

            // Saves quiz completion scores in SharedPreferences the first time.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("quiz_completion", quizzesCompletion);
            editor.commit();
        }

        // set up recycler view
        RecyclerView rv = findViewById(R.id.activity_main_recycler_view);
        rv.setAdapter(new QuizAdapter(this, quizzes, quizTypes));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
