package edu.duke.compsci290.quizmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by mercyfang on 3/5/18.
 */

public class QuizCompletionScoreDialogFragment extends DialogFragment {

    public static QuizCompletionScoreDialogFragment newInstance(
            String score, String completion, int quizIndex) {
        QuizCompletionScoreDialogFragment dialogFragment = new QuizCompletionScoreDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", score);
        bundle.putString("title", completion);
        bundle.putInt("index", quizIndex);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Builds an Alert Dialog to display scores of a quiz if present. The dialog also
        // lets the user to choose to set the quiz as incomplete and then erases all of
        // its scores information.
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.mainActivity);
        String score = getArguments().getString("message");
        score = score.equals("-1") ? "No Score" : score;
        builder.setMessage(score)
                .setTitle(getArguments().getString("title"));

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });

        // If quiz is incomplete, no negative Button is set.
        if (getArguments().getString("title").equals(getString(R.string.complete))) {
            builder.setNegativeButton(
                    "Set to incomplete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // Clears the quiz completion score for current quiz in SharedPreferences.
                    SharedPreferences prefs = MainActivity.mainActivity
                            .getSharedPreferences("QuizScreen", MainActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();

                    String[] quizCompletionScoreArray =
                            prefs.getString("quiz_completion", "").split(";");
                    quizCompletionScoreArray[getArguments().getInt("index")] = "-1";
                    editor.putString(
                            "quiz_completion",
                            TextUtils.join(";", quizCompletionScoreArray));
                    editor.commit();
                }
            });
        }

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
