package edu.duke.compsci290.quizmaster;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by barbaraxiong on 1/25/18.
 */
public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private Context mContext;
    private String[] mQuestions;
    private String[] mChoices1;
    private String[] mChoices2;
    private String[] mChoices3;
    private String[] mChoices4;
    private String[] mChoices5;

    private static final String TAG = QuizAdapter.class.getName();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private TextView mQuestion;
        private RadioGroup mChoices;
        private TextView mChoice1;
        private TextView mChoice2;
        private TextView mChoice3;
        private TextView mChoice4;
        private TextView mChoice5;

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

    public QuizAdapter(final Context context, ArrayList<Question> questions, String[] choice1,
                       String[] choice2, String[] choice3, String[] choice4, String[] choice5) {
        mQuestions = new String[questions.size()];
        int idx = 0;
        for (Question question : questions) {
            mQuestions[idx] = question.getQuestion();
            idx++;
        }
        mChoices1 = choice1;
        mChoices2 = choice2;
        mChoices3 = choice3;
        mChoices4 = choice4;
        mChoices5 = choice5;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mQuestions.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.question_holder, parent, false);
        final ViewHolder questionHolder = new ViewHolder(row);
        return questionHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Todo(mercyfang): better way to deal with finding the correct question.
        String[][] bruteForce = { mChoices1, mChoices2, mChoices3, mChoices4, mChoices5 };

        holder.mQuestion.setText(mQuestions[position]);
        holder.mChoice1.setText(bruteForce[position][0]);
        holder.mChoice2.setText(bruteForce[position][1]);
        holder.mChoice3.setText(bruteForce[position][2]);
        holder.mChoice4.setText(bruteForce[position][3]);
        holder.mChoice5.setText(bruteForce[position][4]);
    }

    public double computeScore() {
        return 100.0;
    }
}
