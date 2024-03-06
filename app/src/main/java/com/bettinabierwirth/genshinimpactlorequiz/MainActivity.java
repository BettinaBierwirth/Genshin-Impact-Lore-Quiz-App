package com.bettinabierwirth.genshinimpactlorequiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


/**
 * The main activity of the Genshin Impact Lore Quiz application.
 * This activity is responsible for handling the main screen and starting the quiz.
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting.
     * This method is responsible for initializing the main activity and setting up the UI.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the 'Start Quiz' button and set a click listener to start the quiz

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(v -> startQuiz());
    }

    /**
     * Opens the QuizActivity to start the quiz.
     */
    private void startQuiz() {
        // Create an intent to navigate to the QuizActivity
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        // Start the QuizActivity
        startActivity(intent);
    }

}