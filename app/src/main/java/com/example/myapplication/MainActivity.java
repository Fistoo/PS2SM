package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String QUIZ_TAG = "MainActivity";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.example.myapplication.PromptActivity.correctAnswer";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptBotton;
    private TextView questionTextView;
    private int currentindex = 0;


    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG,"Wywołana została metoda onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX,currentindex);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(QUIZ_TAG,"Wywołana została metoda cyklu życia on_Create");
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            currentindex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        promptBotton= findViewById(R.id.hint);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }

        });
        promptBotton.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[currentindex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
            startActivity(intent);
        });
        falseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }

        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentindex = (currentindex + 1) % questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();

    }

    @Override
    protected void onStart() {
        super.onStart();
                Log.d(QUIZ_TAG,"Wywołana została metoda cyklu życia onStart ");
    }
    @Override
    protected void onResume() {
        super.onResume();
                Log.d(QUIZ_TAG,"Wywołana została metoda cyklu życia onResume ");
    }
    @Override
    protected void onPause() {
        super.onPause();
                Log.d(QUIZ_TAG,"Wywołana została metoda cyklu życia onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
                Log.d(QUIZ_TAG,"Wywołana została metoda cyklu życia onStop ");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
                Log.d(QUIZ_TAG,"Wywołana została metoda cyklu życia onDestroy");
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentindex].getQuestionId());
    }


    private Question [] questions = new Question[]{
            new Question(R.string.q_1,true),
            new Question(R.string.q_2,false),
            new Question(R.string.q_3,true),
            new Question(R.string.q_4,false),
            new Question(R.string.q_5,true)
    };
    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentindex].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
        }else{
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();

    }
}