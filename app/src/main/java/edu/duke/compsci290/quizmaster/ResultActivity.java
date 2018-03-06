package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

        changeQuizCompletion(quizScore, quizName);

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

    // Changes quizzes scores in SharedPreferences.
    private void changeQuizCompletion(String quizScore, String quizName) {
        SharedPreferences prefs = getSharedPreferences("QuizScreen", MODE_PRIVATE);
        String quizCompletion = prefs.getString("quiz_completion", "");
        if (quizCompletion.isEmpty()) {
            Log.d("ResultActivity",
                    "No quiz_completion scores string is saved in SharedPreferences.");
        }
        String[] quizCompletionArray = quizCompletion.split(";");

        // Finds the index of the current quiz.
        String[] quizNames = getResources().getStringArray(R.array.quiz_names);
        quizName = TextUtils.join("_", quizName.split(" "));
        for (int i = 0; i < quizNames.length; i++) {
            if (quizNames[i].equals(quizName)) {
                // Adds new quiz score to the existed set of scores separated by "\n".
                String quizCompletionScore = quizCompletionArray[i];
                quizCompletionArray[i] = quizCompletionScore.equals("-1")
                        ? quizScore : quizCompletionScore + "\n" + quizScore;
                break;
            }
        }
        prefs.edit()
                .putString(
                        "quiz_completion", TextUtils.join(";", quizCompletionArray))
                .commit();
    }
}
