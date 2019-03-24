package ru.redshart.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private float rightAnswerCounter = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_mideast, true)
    };


    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        mQuestionTextView = findViewById(R.id.question_text_view);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                rightAnswerCounter(true);
            }

        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                rightAnswerCounter(false);
            }
        });

        mNextButton = findViewById(R.id.next_button); // ссылка на кнопку
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton = findViewById(R.id.prev_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }


    private void checkAnswer(boolean userPressedTrue) {
        boolean answerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();
    }


    public void updateQuestion() {
        mTrueButton.setEnabled(true);
        mFalseButton.setEnabled(true);

        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    public void rightAnswerCounter(boolean userPressedTrue) {

        boolean answerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        if ((mCurrentIndex+1) != mQuestionBank.length) {
            if (userPressedTrue == answerTrue) {
                rightAnswerCounter++;
            }
        } else {
            int winRightPercent = Math.round(rightAnswerCounter/mQuestionBank.length*100);
            System.out.println(rightAnswerCounter);
            Toast.makeText(QuizActivity.this,"Вы оказались правы на " + winRightPercent + "%", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanseState) {
        super.onSaveInstanceState(savedInstanseState);
        Log.i(TAG, "onSaveInstanceState: ");
        savedInstanseState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}


