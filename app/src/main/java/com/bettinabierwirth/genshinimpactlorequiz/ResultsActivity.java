package com.bettinabierwirth.genshinimpactlorequiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
/**
 * Activity to display the quiz results, including the score, result message, and result image.
 */
public class ResultsActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created. Initializes UI components,
     * retrieves the score from the intent, and sets up event listeners.
     *
     * @param savedInstanceState The saved instance state.
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Retrieve the score and total questions from the intent
        int score = getIntent().getIntExtra(QuizActivity.EXTRA_SCORE, 0);
        int totalQuestions = 20;

        // Display the result message based on the score
        TextView textViewResult = findViewById(R.id.text_view_result);
        TextView resultMessage = findViewById(R.id.text_view_result_message);
        resultMessage.setText(getResultMessage(score, totalQuestions));

        // Set the result image based on the score
        ImageView resultImage = findViewById(R.id.image_view_result);
        resultImage.setImageResource(getResultImage(score, totalQuestions));

        // Set the OnClickListener for the Start Quiz button
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(v -> startQuiz());
        // Display the total score
        textViewResult.setText(getString(R.string.your_score) + score);
    }


    /**
     * Retrieves the appropriate result message based on the score percentage.
     *
     * @param score           The user's score.
     * @param totalQuestions  The total number of questions in the quiz.
     * @return A string containing the result message.
     */
    private String getResultMessage(int score, int totalQuestions) {
        double percentage = (double) score / totalQuestions * 100;

        if (score == 0) {
            return "What a Loser. Do you even play the game?";
        } else if (score == totalQuestions) {
            return "Congratulations! You did very well!";
        } else if (percentage > 0 && percentage <= 25) {
            return "Well it's...something.";
        } else if (percentage > 25 && percentage <= 50) {
            return "You're trying.";
        } else if (percentage > 50 && percentage <= 75) {
            return "Well done!";
        } else if (percentage > 75 && percentage < 100) {
            return "Here is a dodoco for you!";
        } else {
            return "Invalid score. Try again.";
        }
    }


    /**
     * Retrieves the appropriate result image based on the score percentage.
     *
     * @param score           The user's score.
     * @param totalQuestions  The total number of questions in the quiz.
     * @return The resource ID of the result image.
     */
    private int getResultImage(int score, int totalQuestions) {
        double percentage = (double) score / totalQuestions * 100;

        if (score == 0) {
            return R.drawable.wanderer_1;
        } else if (percentage > 0 && percentage <= 25) {
            return R.drawable.wriothesley_1;
        } else if (percentage > 25 && percentage <= 50) {
            return R.drawable.razor_1;
        } else if (percentage > 50 && percentage <= 75) {
            return R.drawable.yaoyao_1;
        } else if (percentage > 75 && percentage < 100) {
            return R.drawable.klee_1;
        } else {
            return R.drawable.neuvillette_1;
        }
    }


    /**
     * Starts a new quiz by navigating to the QuizActivity.
     * Clears the score from the intent to ensure a fresh start.
     * Finishes the current activity to clear the results.
     */
    private void startQuiz() {
        // Clear the score from the intent, so it doesn't carry over to the new quiz
        getIntent().removeExtra(QuizActivity.EXTRA_SCORE);

        Intent intent = new Intent(ResultsActivity.this, QuizActivity.class);
        startActivity(intent);

        finish();
    }
}
