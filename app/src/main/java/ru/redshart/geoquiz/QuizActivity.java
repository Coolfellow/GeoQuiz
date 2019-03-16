package ru.redshart.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP,0,200);
                t.show();
            }

        });


        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = makeText(QuizActivity.this, "Incorrect!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP,0,200);
                t.show();
            }
        });
    }
}

