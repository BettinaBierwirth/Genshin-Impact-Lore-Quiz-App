package com.bettinabierwirth.genshinimpactlorequiz;

import android.provider.BaseColumns;

//Dieser Code ist dazu da um Konstanten zu erstellen und modifizieren für Änderungen in der Datenbank
public final class QuizContract {

    private QuizContract() {}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";

        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}