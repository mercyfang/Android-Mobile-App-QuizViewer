package edu.duke.compsci290.quizmaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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

        RecyclerView rv = findViewById(R.id.activity_main_recycler_view);
        rv.setAdapter(new QuizAdapter(this, quizzes, quizTypes));
        rv.setLayoutManager(new LinearLayoutManager(this));

        Button newQuizButton = findViewById(R.id.new_quiz_button);
        newQuizButton.setText("New Quiz");

        String[] newQuizzes = this.getResources().getStringArray(R.array.new_quiz_names);
        String[] newQuizTypes = this.getResources().getStringArray(R.array.new_quiz_types);
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Shows dialog to let user choose new quiz type.
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle("Pick a quiz type");
                String[] quizTypes = new String[] {"linear", "personality"};
                builder.setItems(quizTypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int index) {
                        if (index == 0) { // Linear quiz.
                            openQuiz(newQuizzes[0], newQuizTypes[0]);
                        } else if (index == 1) { // Personality quiz.
                            openQuiz(newQuizzes[1], newQuizTypes[1]);
                        }
                    }
                });
                builder.show();
            }

            private void openQuiz(String quizName, String quizType) {
                Intent intent = new Intent(getApplicationContext(), QuizScreen.class);
                intent.putExtra("quiz_name_key", quizName);
                intent.putExtra("quiz_type_key", quizType);
                getApplicationContext().startActivity(intent);
            }
        });
    }
}
