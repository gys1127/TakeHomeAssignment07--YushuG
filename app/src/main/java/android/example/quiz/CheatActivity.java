package android.example.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private Button mCheatButton;
    private TextView mAnswerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Intent intent = getIntent();
        final boolean answer = intent.getBooleanExtra(Keys.QUESTION_ANSWER_KEY, true);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mAnswerTextView.setText("");

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(String.valueOf(answer));

                Intent data = new Intent();
                data.putExtra(Keys.IF_CHEATED_KEY, true);
                setResult(RESULT_OK, data);
            }
        });
    }
}
