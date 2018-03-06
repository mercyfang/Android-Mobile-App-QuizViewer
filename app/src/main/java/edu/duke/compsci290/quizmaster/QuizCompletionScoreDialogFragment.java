package edu.duke.compsci290.quizmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;

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
                    "set to incomplete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // Clears the quiz completion score for current quiz in SharedPreferences.
                    SharedPreferences prefs = MainActivity.mainActivity
                            .getSharedPreferences("QuizScreen", MainActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();

                    int quizIndex = getArguments().getInt("index");
                    String[] quizCompletionScoreArray =
                            prefs.getString("quiz_completion", "").split(";");
                    quizCompletionScoreArray[quizIndex] = "-1";
                    editor.putString(
                            "quiz_completion",
                            TextUtils.join(";", quizCompletionScoreArray));
                    editor.commit();

                    // Resets quizCompletion button text to "Incomplete".
                    RecyclerView recyclerView =
                            MainActivity.mainActivity.findViewById(
                                    R.id.activity_main_recycler_view);
                    Button quizCompletionButton = recyclerView
                            .findViewHolderForAdapterPosition(quizIndex)
                            .itemView
                            .findViewById(R.id.quiz_completion_button);
                    quizCompletionButton.setText(getString(R.string.incomplete));
                }
            });
        }

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
