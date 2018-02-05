package edu.duke.compsci290.quizmaster;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by barbaraxiong on 1/25/18.
 */
import java.util.Arrays;

import edu.duke.compsci290.quizmaster.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>{
    Context mContext;
    String[] mQuestions;
    String[] mChoice1;
    String[] mChoice2;
    String[] mChoice3;
    String[] mChoice4;
    String[] mChoice5;

    private static final String TAG = QuizAdapter.class.getName();
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        TextView mQuestion;
        RadioGroup mChoices;
        TextView mChoice1;
        TextView mChoice2;
        TextView mChoice3;
        TextView mChoice4;
        TextView mChoice5;


        public ViewHolder(View itemView) {
            super(itemView);
            this.mLinearLayout = itemView.findViewById(R.id.question_holder_linear_layout);
            this.mQuestion = itemView.findViewById(R.id.question_text_view);
            this.mChoices = itemView.findViewById(R.id.choices_radio_group);
            this.mChoice1 = itemView.findViewById(R.id.choice1_radio_button);
            this.mChoice2 = itemView.findViewById(R.id.choice2_radio_button);
            this.mChoice3 = itemView.findViewById(R.id.choice3_radio_button);
            this.mChoice4 = itemView.findViewById(R.id.choice4_radio_button);
            this.mChoice5 = itemView.findViewById(R.id.choice5_radio_button);

            // connect other UI components
        }
    }

    public QuizAdapter(final Context context, String[] questions, String[] choice1, String[] choice2, String[] choice3, String[] choice4, String[] choice5) {
        this.mQuestions = questions;
        this.mChoice1 = choice1;
        this.mChoice2 = choice2;
        this.mChoice3 = choice3;
        this.mChoice4 = choice4;
        this.mChoice5 = choice5;
        this.mContext = context;
    }


    @Override
    public int getItemCount(){
        return mQuestions.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.question_holder, parent, false);
        final ViewHolder questionHolder = new ViewHolder(row);


        return questionHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.mQuestion.setText(mQuestions[position]);
        holder.mChoice1.setText(mChoice1[position]);
        holder.mChoice2.setText(mChoice2[position]);
        holder.mChoice3.setText(mChoice3[position]);
        holder.mChoice4.setText(mChoice4[position]);
        holder.mChoice5.setText(mChoice5[position]);

    }



}
