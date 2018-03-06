package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;

public class QuizScreen extends AppCompatActivity {
    private Button mNextButton;
    private Quiz mQuiz;
    private String mQuizName;
    private String mQuizIdentifier;
    private TextView mQuestionView;
    private int mQuestionIndex;
    private Question mCurrentQuestion;
    private String mQuizType;
    private JSONQuizGenerator mJSONQuizGenerator;
    private RadioGroup mChoices;
    private TextView mChoice1;
    private TextView mChoice2;
    private TextView mChoice3;
    private TextView mChoice4;
    private TextView mChoice5;

    // For nonlinear quiz, if the last easy question is correct, we go to the next hard question.
    // Otherwise we continue with easy question. If the last hard question is correct, we continue
    // with hard question until hard questions run out. Otherwise, we go back to easy question.
    private boolean lastQuestionCorrect;

    private static String INDEX = "INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_screen);

        Intent receivedIntent = this.getIntent();
        mQuizIdentifier = String.valueOf(receivedIntent.getStringExtra("quiz_name_key"));
        mQuizType = String.valueOf(receivedIntent.getStringExtra("quiz_type_key"));
        try {
            int quizId = getApplicationContext().getResources().getIdentifier(
                    mQuizIdentifier, "string", getApplicationContext().getPackageName());
            mJSONQuizGenerator = new JSONQuizGenerator(quizId);
        } catch (MalformedURLException e) {
            Log.d("APPMAIN","could not create JSON quiz");
            e.printStackTrace();
        }

        String json = "";
        try {
            json = mJSONQuizGenerator.getJSON(this);
        } catch (Exception e) {
            // Fails to find the quiz, return back to MainActivity.
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            getApplicationContext().startActivity(intent);
            return;
        }
        mQuiz = JSONParser.parse(json, mQuizType);
        mQuizName = mQuiz.getQuizName();
        mQuestionView = this.findViewById(R.id.question_text_view);
        mNextButton = this.findViewById(R.id.next_button);
        mQuestionIndex = 0;
        mChoices = this.findViewById(R.id.choices_radio_group);
        mChoice1 = this.findViewById(R.id.choice1_radio_button);
        mChoice2 = this.findViewById(R.id.choice2_radio_button);
        mChoice3 = this.findViewById(R.id.choice3_radio_button);
        mChoice4 = this.findViewById(R.id.choice4_radio_button);
        mChoice5 = this.findViewById(R.id.choice5_radio_button);

        lastQuestionCorrect = false;

        if (mQuizType.equals("nonlinear")) {
            askQuestionForNonLinearQuiz();
        } else {
            askQuestion();
        }

        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextQuestion();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        // Restores the instance when application gets killed or in case of going to ResultScreen,
        // we remove the key value pairs in ResultScreen class.
        SharedPreferences prefs = getSharedPreferences("QuizScreen", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("quiz_name_key", mQuizName);
        editor.putString("quiz_type_key", mQuizType);
        editor.commit();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mQuestionIndex = savedInstanceState.getInt(INDEX);
        if (mQuizType.equals("nonlinear")) {
            askQuestionForNonLinearQuiz();
        } else {
            askQuestion();
        }
        // Restores mQuestionIndex to the current index.
        mQuestionIndex--;
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        state.putInt(INDEX, mQuestionIndex);
        super.onSaveInstanceState(state);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void nextQuestion() {
        updateScoreAndToastMessage();
        mChoices.clearCheck();
        if (mQuizType.equals("nonlinear")) {
            askQuestionForNonLinearQuiz();
        }
        if (mQuestionIndex < mQuiz.getQuestionAmount() - 1) {
            if (!mQuizType.equals("nonlinear")) {
                mQuestionIndex++;
                askQuestion();
            }
        } else {
            // In case of last question, show the result view.
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            String displayContent = "";
            try {
                displayContent = mQuiz.processResult();
            } catch (QuizResultException e) {
                displayContent = "No Result";
            }
            switch (mQuizType) {
                case "linear":
                    displayContent += " out of " + Integer.toString(mQuiz.getQuestionAmount());
                    break;
                case "nonlinear":
                    NonLinearQuiz quiz = (NonLinearQuiz) mQuiz;
                    // The maximum score a user could get is the twice the total number of hard
                    // questions plus one for the first easy question, according our score
                    // calculation principle.
                    displayContent +=
                            " out of " + Integer.toString(quiz.getHardQuestionCount() * 2 + 1);
                default:
                    break;
            }
            intent.putExtra(getApplicationContext().getString(R.string.scorekey),
                    displayContent);
            intent.putExtra(
                    getApplicationContext().getString(R.string.quiz_name),
                    processQuizName(mQuizName));
            getApplicationContext().startActivity(intent);
        }
    }

    private void updateScoreAndToastMessage() {
        int checkedRadioButtonId = mChoices.getCheckedRadioButtonId();
        String selectedButtonText = "";
        if (checkedRadioButtonId != -1) {
            RadioButton selectedButton = mChoices.findViewById(checkedRadioButtonId);
            selectedButtonText = selectedButton.getText().toString();
        }
        switch (mQuizType) {
            case "linear": case "nonlinear":
                mCurrentQuestion.processChosen(selectedButtonText);
                if (mCurrentQuestion.getAttribute(selectedButtonText)
                        .equals("correct")) {
                    lastQuestionCorrect = true;
                    mQuiz.updateScore();

                    // For nonlinear quiz, we give extra point for hard questions.
                    if (mQuizType.equals("nonlinear") &&
                            mCurrentQuestion.getDifficulty().equals("hard")) {
                        mQuiz.updateScore();
                    }

                    Toast.makeText(QuizScreen.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    lastQuestionCorrect = false;
                    Toast.makeText(QuizScreen.this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
                break;
            case "personality":
                mCurrentQuestion.processChosen(selectedButtonText);
                break;
            default:
                Log.d("QuizScreen", "quiz type is not specified");
                break;
        }
    }

    private void askQuestionForNonLinearQuiz() {
        NonLinearQuiz nonLinearQuiz = (NonLinearQuiz) mQuiz;
        Question nextQuestion;
        if (lastQuestionCorrect) {
            if (nonLinearQuiz.getHardQuestions().hasNext()) {
                nextQuestion = nonLinearQuiz.getHardQuestions().next();
            } else {
                // Hack QuestionIdx to be the last index to stop the quiz.
                mQuestionIndex = mQuiz.getQuestionAmount();
                return;
            }
        } else {
            if (nonLinearQuiz.getEasyQuestions().hasNext()) {
                nextQuestion = nonLinearQuiz.getEasyQuestions().next();
            } else {
                // Hack QuestionIdx to be the last index to stop the quiz.
                mQuestionIndex = mQuiz.getQuestionAmount();
                return;
            }
        }
        mCurrentQuestion = nextQuestion;
        mQuestionView.setText(nextQuestion.getQuestion());
        mChoice1.setText(nextQuestion.getAnswer(0).getAnswer());
        mChoice2.setText(nextQuestion.getAnswer(1).getAnswer());
        mChoice3.setText(nextQuestion.getAnswer(2).getAnswer());
        mChoice4.setText(nextQuestion.getAnswer(3).getAnswer());
        mChoice5.setText(nextQuestion.getAnswer(4).getAnswer());
    }

    private void askQuestion() {
        Question q = mQuiz.getQuestion(mQuestionIndex);
        mCurrentQuestion = q;
        mQuestionView.setText(q.getQuestion());
        mChoice1.setText(q.getAnswer(0).getAnswer());
        mChoice2.setText(q.getAnswer(1).getAnswer());
        mChoice3.setText(q.getAnswer(2).getAnswer());
        mChoice4.setText(q.getAnswer(3).getAnswer());
        mChoice5.setText(q.getAnswer(4).getAnswer());
    }

    private String processQuizName(String quizName) {
        return quizName.replaceAll("_", " ");
    }
}
