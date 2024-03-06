package com.bettinabierwirth.genshinimpactlorequiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/**
 * The QuizActivity class represents the screen where users answer quiz questions.
 * It handles the display of questions, user input, scoring, and countdown timer.
 */
public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private static final int MAX_QUESTIONS = 13;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ImageView imageViewQuestion;

    private Set<Integer> usedQuestionIndices;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usedQuestionIndices = new HashSet<>();

        textViewQuestion = findViewById(R.id.text_view_question);
        imageViewQuestion = findViewById(R.id.image_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();


        QuizDbHelper dbHelper = new QuizDbHelper(this);

        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }
    /**
     * Displays the next question in the quiz.
     * Resets radio button text colors and clears the radio group selection.
     * Generates and displays a new question until the specified number of questions is reached.
     * Adds the index of the displayed question to the set of used questions to ensure uniqueness.
     * Updates the UI with the question text, options, and image (if available).
     * Initiates the countdown timer for the current question.
     * If the maximum number of questions is reached, finishes the quiz and navigates to the ResultsActivity.
     */
    private void showNextQuestion() {
        // Reset radio button text colors and clear radio group selection
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        // Check if the maximum number of questions is reached
        if (questionCounter < MAX_QUESTIONS) {
            // Display the next question

            // Keep generating a new question until an unused one is found
            do {
                currentQuestion = questionList.get(questionCounter);
            } while (usedQuestionIndices.contains(questionCounter));

            // Add the index to the set of used questions
            usedQuestionIndices.add(questionCounter);

            // Display question text
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            // Display image if available
            int imageReference = currentQuestion.getImageReference();
            if (imageReference != 0) {
                imageViewQuestion.setImageResource(imageReference);
                imageViewQuestion.setVisibility(View.VISIBLE);
            } else {
                imageViewQuestion.setVisibility(View.GONE);
            }
            // Update question counter and UI elements
            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + Math.min(MAX_QUESTIONS, questionCountTotal));
            answered = false;
            buttonConfirmNext.setText("Confirm");
            // Reset and start the countdown timer for the current question
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            // Finish the quiz if the maximum number of questions is reached
            finishQuiz();
        }
    }

    /**
     * Initiates and starts the countdown timer for the current question.
     * The timer ticks every second, updating the remaining time and calling updateCountDownText().
     * When the timer finishes, sets the remaining time to 0, updates the countdown text,
     * and calls the checkAnswer() method to handle the user's response.
     */
    private void startCountDown() {
        // Initialize and start the countdown timer
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the remaining time and call updateCountDownText()
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                // Set remaining time to 0, update countdown text, and check the user's answer
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }
    /**
     * Updates the text displaying the countdown timer.
     * Calculates and formats the remaining time in minutes and seconds.
     * Sets the formatted time to the textViewCountDown and adjusts the text color based on remaining time.
     */
    private void updateCountDownText() {
        // Calculate minutes and seconds from remaining time
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        // Format the time as a string with leading zeros
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        // Set the formatted time to the textViewCountDown
        textViewCountDown.setText(timeFormatted);

        // Adjust text color based on remaining time
        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }
    /**
     * Checks the selected answer against the correct answer and updates the score.
     * Marks the question as answered, cancels the countdown timer, and identifies the selected radio button.
     * Compares the selected answer's index with the correct answer's index in the radio group.
     * If the answers match, increments the score and updates the score display.
     * Calls the showSolution() method to highlight correct and incorrect answers.
     */
    private void checkAnswer() {
        // Mark the question as answered and cancel the countdown timer
        answered = true;
        countDownTimer.cancel();

        // Identify the selected radio button
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        // Check if the selected answer is correct
        if (answerNr == currentQuestion.getAnswerNr()) {
            // Increment the score and update the score display
            score++;
            textViewScore.setText("Score: " + score);
        }
        // Highlight correct and incorrect answers
        showSolution();
    }
    /**
     * Displays the correct answer and updates the UI for the next question.
     * Sets the text color of all radio buttons to red.
     * Identifies the correct answer and changes its corresponding radio button's text color to green.
     * Updates the buttonConfirmNext text based on whether there are more questions.
     */
    private void showSolution() {
        // Set text color of all radio buttons to red
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        // Identify the correct answer and change its text color to green
        switch(currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }

        // Update buttonConfirmNext text based on whether there are more questions
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }

    }
    /**
     * Finishes the quiz and navigates to the ResultsActivity.
     */
    private void finishQuiz() {
        Intent resultIntent = new Intent(QuizActivity.this, ResultsActivity.class);
        resultIntent.putExtra(EXTRA_SCORE, score);
        startActivity(resultIntent);
        finish();
    }
    /**
     * Overrides the onBackPressed method to handle back button presses during the quiz.
     */
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();

        // Move the super.onBackPressed() call here
        super.onBackPressed();
    }

    /**
     * Overrides the onDestroy method to cancel the countdown timer when the activity is destroyed.
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}