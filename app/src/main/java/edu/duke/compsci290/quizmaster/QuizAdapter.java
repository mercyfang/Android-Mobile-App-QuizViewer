package edu.duke.compsci290.quizmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import edu.duke.compsci290.quizmaster.quizutil.QuizCompletionScoreDialogFragment;

/**
 * Created by barbaraxiong on 3/3/18.
 */

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>{
    private Context mContext;
    private String[] mQuizzes;
    private String[] mQuizTypes;

    private final String complete = MainActivity.mainActivity.getString(R.string.complete);
    private final String incomplete = MainActivity.mainActivity.getString(R.string.incomplete);
    private Context resources;

    public Context getResources() {
        return resources;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        Button mQuizButton;
        Button mQuizCompletionButton;
        ViewHolder quizHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            // Connects UI component
            this.mQuizButton = itemView.findViewById(R.id.quiz_button);
            this.mLinearLayout = itemView.findViewById(R.id.quiz_holder_linear_layout);
            this.mQuizCompletionButton = itemView.findViewById(R.id.quiz_completion_button);
        }
    }

    public QuizAdapter(final Context context, String[] quizzes, String[] quizTypes) {
        this.mQuizzes = quizzes;
        this.mQuizTypes = quizTypes;
        this.mContext = context;
    }

    @Override
    public int getItemCount(){
        return mQuizzes.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater mInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.quiz_holder, parent, false);
        final ViewHolder quizHolder= new ViewHolder(row);
        final Button mQuizButton = row.findViewById(R.id.quiz_button);
        final Button mQuizCompletionButton = row.findViewById(R.id.quiz_completion_button);

        mQuizButton.setOnClickListener(new View.OnClickListener() {
            private void openQuiz(String quizName, String quizType) {
                // Puts info for next activity QuizScreen.
                Intent intent = new Intent(mContext, QuizScreen.class);
                intent.putExtra("quiz_name_key", quizName);
                intent.putExtra("quiz_type_key", quizType);

                mContext.startActivity(intent);
            }

            @Override
            public void onClick(View view) {
                openQuiz(mQuizzes[quizHolder.getAdapterPosition()],
                        mQuizTypes[quizHolder.getAdapterPosition()]);
            }
        });

        mQuizCompletionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = MainActivity.mainActivity.getSharedPreferences(
                        "QuizScreen", MainActivity.MODE_PRIVATE);
                String score = prefs.getString("quiz_completion", "")
                        .split(";")[quizHolder.getAdapterPosition()];

                String title = score.equals("-1") ? incomplete : complete;
                QuizCompletionScoreDialogFragment dialogFragment =
                        QuizCompletionScoreDialogFragment.newInstance(
                                score, title, quizHolder.getAdapterPosition());
                dialogFragment.show(
                        MainActivity.mainActivity.getFragmentManager(),"quizCompletionDialog");
            }
        });


        return quizHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String quizType = mQuizTypes[position];
        if (quizType.equals("linear")) {
            holder.mQuizButton.setBackgroundColor(Color.parseColor("#B3A4FF"));
        }
        else if (quizType.equals("personality")) {
            holder.mQuizButton.setBackgroundColor(Color.parseColor("#FF8DD9"));
        }
        else if (quizType.equals("nonlinear")) {
            holder.mQuizButton.setBackgroundColor(Color.parseColor("#FFF98D"));
        }

        holder.mQuizButton.setText(mQuizzes[position].replaceAll("_", " "));

        SharedPreferences prefs = MainActivity.mainActivity.getSharedPreferences(
                "QuizScreen", MainActivity.MODE_PRIVATE);
        // We store quizzes completion score using "-1" as incomplete and join the scores using ';'.
        String score = prefs
                .getString("quiz_completion", "").split(";")[position];
        if (score.equals("-1")) {
            holder.mQuizCompletionButton.setText(incomplete);
            holder.mQuizCompletionButton.setBackgroundColor(Color.parseColor("#FF8A8A"));

        } else {
            holder.mQuizCompletionButton.setText(complete);
            holder.mQuizCompletionButton.setBackgroundColor(Color.parseColor("#8AFFC4"));
        }
    }
}