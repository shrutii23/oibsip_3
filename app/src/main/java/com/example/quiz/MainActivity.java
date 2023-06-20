package com.example.quiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1RadioButton, option2RadioButton, option3RadioButton, option4RadioButton;
    private Button submitButton;
    private ConstraintLayout mainLayout;

    private String[] questions;
    private String[] correctAnswers;
    private int currentQuestionIndex;
    private int score;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        option4RadioButton = findViewById(R.id.option4RadioButton);
        submitButton = findViewById(R.id.submitButton);
        mainLayout = findViewById(R.id.mainLayout);

        // Set up the questions and correct answers
        questions = getResources().getStringArray(R.array.questions);
        correctAnswers = getResources().getStringArray(R.array.correct_answers);

        // Initialize the current question index and score
        currentQuestionIndex = 0;
        score = 0;

        // Display the first question
        displayQuestion(currentQuestionIndex);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check the selected answer
                int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedOption = findViewById(selectedOptionId);

                if (selectedOption != null) {
                    String selectedAnswer = selectedOption.getText().toString();
                    checkAnswer(selectedAnswer);

                    // Move to the next question
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        displayQuestion(currentQuestionIndex);
                    } else {
                        // Display the final score
                        showFinalScore();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void displayQuestion(int questionIndex) {
        questionTextView.setText(questions[questionIndex]);
        option1RadioButton.setText(getResources().getStringArray(R.array.options1)[questionIndex]);
        option2RadioButton.setText(getResources().getStringArray(R.array.options2)[questionIndex]);
        option3RadioButton.setText(getResources().getStringArray(R.array.options3)[questionIndex]);
        option4RadioButton.setText(getResources().getStringArray(R.array.options4)[questionIndex]);

        // Clear the radio button selection
        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = correctAnswers[currentQuestionIndex];
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
        }
    }

    private void showFinalScore() {
        String message = "Final Score: " + score + "/" + questions.length;
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
