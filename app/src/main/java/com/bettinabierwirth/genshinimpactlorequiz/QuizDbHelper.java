package com.bettinabierwirth.genshinimpactlorequiz;

import com.bettinabierwirth.genshinimpactlorequiz.QuizContract.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
/**
 * A helper class for managing the SQLite database used in the Genshin Impact Lore Quiz application.
 * This class provides methods for database creation, upgrades, and interaction with the "Questions" table.
 */
public class QuizDbHelper extends SQLiteOpenHelper {
    // Database name and version
    private static final String DATABASE_NAME = "GenshinImpactLoreQuizApp.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    /**
     * Constructor for the QuizDbHelper class.
     *
     * @param context The context in which the helper is created.
     */

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is first created.
     * Creates the "Questions" table and populates it with sample data.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        // SQL query to create the "Questions" table
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_IMAGE + " INTEGER," +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        // Execute the SQL query to create the table
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        // Populate the "Questions" table with sample data
        fillQuestionsTable();
    }

    /**
     * Called when the database needs to be upgraded.
     * Deletes the existing "Questions" table and calls onCreate to recreate it.
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Delete the existing "Questions" table
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);

        // Recreate the "Questions" table
        onCreate(db);
    }

    /**
     * Fills the "Questions" table with sample data.
     */
    private void fillQuestionsTable() {

        Question q1 = new Question("Whose signature weapon is this?", R.drawable.weapon_polar_star, "Childe", "Venti", "Yelan", "Ganyu", 1);
        addQuestion(q1);
        Question q2 = new Question("What is the name of the restaurant that Xiangling owns with her father?", 0, "Wangshu", "Wanmin", "Lambad's Tavern", "Wangsheng", 2);
        addQuestion(q2);
        Question q3 = new Question("During which Trounce Domain Fight is the player given a Neo Akasha Terminal?", 0, "Azhdaha", "Raiden", "Wolf of the North", "Shouki no Kami, The Prodigal", 4);
        addQuestion(q3);
        Question q4 = new Question("What is the name of the festival meant to celebrate Lesser Lord Kusanali's birthday?", 0, "Sabzeruz", "Ludi Harpastum", "Samsara", "Irodori", 1);
        addQuestion(q4);
        Question q5 = new Question("What is Lisa's last name?", 0, "Pegg", "Minci", "Gunnhildr", "Junot", 2);
        addQuestion(q5);
        Question q6 = new Question("Which fruit motif is repeated frequently during the quest \"The Temple Where Sand Flows Like Tears\"", 0, "Pomegranate", "Apple", "Persimmon", "Cherry", 1);
        addQuestion(q6);
        Question q7 = new Question("Who is the cat who oversees the Asase Shrine?", 0, "Hibiki", "Etsuko", "Junpei", "Neko", 4);
        addQuestion(q7);
        Question q8 = new Question("What type of food does Zhongli dislike?", 0, "Meat", "Fried Food", "Seafood", "Spicy Food", 3);
        addQuestion(q8);
        Question q9 = new Question("Which of the following characters is a vegetarian?", 0, "Ganyu", "Albedo", "Baizhu", "Kazuha", 1);
        addQuestion(q9);
        Question q10 = new Question("Which character rightfully owns the title of \"God of the Stove\"?", 0, "Zhongli", "Guoba", "Moon Carver", "Bosacius", 2);
        addQuestion(q10);
        Question q11 = new Question("Which character assisted Yelan in gaining control of the Yanshang Teahouse?", 0, "Keqing", "Ningguang", "Childe", "Yanfei", 3);
        addQuestion(q11);
        Question q12 = new Question("Lyney, Lynette, and Freminet were raised in an orphanage that’s currently under the control of \"Father\", The Knave. What is the name of this orphanage?", 0, "Bertin's House", "House of the Hearth", "Fountain of Youth", "Damoville Purveyor", 2);
        addQuestion(q12);
        Question q13 = new Question("What's the name of this item?", R.drawable.cloudseam_scale, "Jade Scale", "Cloudseam Scale", "Cloud Jade", "Cloudseam Jade", 2);
        addQuestion(q13);
        Question q14 = new Question("What's the name of this item?", R.drawable.parametric_transformer, "Systematic Transposer", "Variant Transmogrifier", "Dynamic Transformer", "Parametric Transformer", 4);
        addQuestion(q14);
        Question q15 = new Question("What's the name of this weapon?", R.drawable.jadefalls_splendor, "Verdant Majesty's Grace", "Jadefall's Splendor", "Jade Essence Radiance", "Emerald Cascade Elegance", 2);
        addQuestion(q15);
        Question q16 = new Question("Which character needs this item to ascend?", R.drawable.lakelight_lily, "Wanderer", "Neuvillette", "Furina", "Nilou", 3);
        addQuestion(q16);
        Question q17 = new Question("Who's special dish is this?", R.drawable.once_upon_a_time_in_mondstadt, "Diluc", "Mona", "Zhongli", "Childe", 1);
        addQuestion(q17);

    }

    /**
     * Adds a question to the "Questions" table.
     *
     * @param question The question to be added.
     */
    private void addQuestion(Question question) {
        // ContentValues object to store the question
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_IMAGE, question.getImageReference());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());

        // Insert the question into the "Questions" table
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    /**
     * Retrieves all questions from the "Questions" table.
     *
     * @return A list of Question objects representing all the questions in the table.
     */
    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        // Query to select all columns from the "Questions" table
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            // Iterate through the cursor to retrieve each question
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setImageReference(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_IMAGE)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
