package android.example.quiz;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    private static final Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_china, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false)
    };

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mPrevButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;
    private boolean mCheated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheated) {
                    Toast.makeText(MainActivity.this, R.string.cheated_toast, Toast.LENGTH_SHORT).show();
                } else if (mQuestionBank[mCurrentIndex].ismAnswerTrue()) {
                    Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheated) {
                    Toast.makeText(MainActivity.this, R.string.cheated_toast, Toast.LENGTH_SHORT).show();
                } else if (mQuestionBank[mCurrentIndex].ismAnswerTrue()) {
                    Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheated = false;
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getmTextResId();
                mQuestionTextView.setText(question);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheated = false;
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getmTextResId();
                mQuestionTextView.setText(question);
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                intent.putExtra(Keys.QUESTION_ANSWER_KEY, mQuestionBank[mCurrentIndex].ismAnswerTrue());
                startActivityForResult(intent, RequestCodes.IF_CHEATED_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RequestCodes.IF_CHEATED_REQUEST_CODE && resultCode == RESULT_OK) {
            mCheated = data.getBooleanExtra(Keys.IF_CHEATED_KEY, false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
