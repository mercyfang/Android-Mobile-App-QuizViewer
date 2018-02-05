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
import edu.duke.compsci290.quizmaster.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>{
    Context mContext;
    String[] mQuestions;
    String[] mChoices;

    private static final String TAG = QuizAdapter.class.getName();
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        TextView mQuestion;
        RadioGroup mChoices;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mLinearLayout = itemView.findViewById(R.id.question_holder_linear_layout);
            this.mQuestion = itemView.findViewById(R.id.question_text_view);
            this.mChoices = itemView.findViewById(R.id.choices_radio_group);

            // connect other UI components
        }
    }

    public QuizAdapter(final Context context, String[] questions) {
        this.mQuestions = questions;
        //this.mChoices = choices;
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
        // set RadioGroup

    }



}
