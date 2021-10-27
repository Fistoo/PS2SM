package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
private boolean correctAnswer;
private Button showCorrectAnsewrButton;
private TextView answerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);

        answerTextView = findViewById(R.id.answer_text_view);
        showCorrectAnsewrButton = findViewById(R.id.answer_button);

        showCorrectAnsewrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);
            }
        });
    }
}