package edu.duke.compsci290.quizmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by barbaraxiong on 3/3/18.
 */

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>{
    private Context mContext;
    private String[] mQuizzes;
    private String[] mQuizTypes;

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        Button mQuizButton;

        public ViewHolder(View itemView) {
            super(itemView);
            // Connects UI component
            this.mQuizButton = itemView.findViewById(R.id.quiz_button);
            this.mLinearLayout = itemView.findViewById(R.id.quiz_holder_linear_layout);
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
        return quizHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mQuizButton.setText(mQuizzes[position].replaceAll("_", " "));
    }
}