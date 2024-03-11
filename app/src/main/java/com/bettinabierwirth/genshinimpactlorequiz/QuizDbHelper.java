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
    private static final int DATABASE_VERSION = 2;

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
        Question q18 = new Question("Which artifact set primarily references the Mare Jivari?", 0, "Lavawalker", "Golden Troupe", "Desert Pavilion Chronicle", "Crimson Witch of Flames", 1);
        addQuestion(q18);
        Question q19 = new Question("Which Harbinger is known as \"The Jester\"?", 0, "Arlecchino", "Sandrone", "Pierro", "Capitano", 3);
        addQuestion(q19);
        Question q20 = new Question("Who is Nabu Malikata?", 0, "King Deshret", "Greater Lord Rukkhadevata", "Liloupar", "The Goddess of Flowers", 4);
        addQuestion(q20);
        Question q21 = new Question("According to The \"Byakuyakoku Collection\", what walks on four feet in the early morning, two feet during the day, and three feet at night?", 0, "A human", "A vishap", "A hilichurl", "A sovereign", 2);
        addQuestion(q21);
        Question q22 = new Question("What caused the spread of forbidden knowledge in Sumeru?", 0, "Greater Lord Rukkhadevata pursued the knowledge to save her people from famine.", "King Deshret unleashed the forbidden knowledge in order to revive The Goddess of Flowers.", "Apep devoured King Deshret, who had accidentally consumed the forbidden knowledge.", "Forbidden knowledge was unleashed when the Heavenly Principles pierced Apep with a Divine Nail.", 3);
        addQuestion(q22);
        Question q23 = new Question("What process creates Consecrated Beast?", 0, "Smaller lifeforms devouring the remains of stronger lifeforms, such as Gods.", "The devout followers of Gods become powerful after centuries of strenuous training.", "The Withering defiles smaller lifeforms and causes them to grow dangerously powerful.", "Consecrated Beasts are a result of the distortions caused by Ley Lines.", 1);
        addQuestion(q23);
        Question q24 = new Question("Which of the following is not one of the Three Sisters of the Lunar Palace?", 0, "Canon", "Aria", "Sonnet", "Melody", 4);
        addQuestion(q24);
        Question q25 = new Question("According to legend, why did the Seelies lose their powers and intelligence?", 0, "They fell in love with humans", "They betrayed the Heavenly Principles", "They conspired with Egeria to create new lifeforms", "They assisted Khaenri'ah during the cataclysm", 1);
        addQuestion(q25);
        Question q26 = new Question("Who assisted the Pari in protecting the Vourukasha Oasis during the cataclysm?", 0, "Nabu Malikata", "Dainsleif", "Dawn Knight Ragnvindr", "Egeria", 2);
        addQuestion(q26);
        Question q27 = new Question("What is a \"Descender\"?", 0, "A person who betrays Celestia's laws", "Another name for a sovereign", "An individual from a nation not under an Archon's rule", "A being that came to Teyvat from another world", 4);
        addQuestion(q27);
        Question q28 = new Question("Who is the fourth descender?", 0, "The Heavenly Principles", "The traveler", "Paimon", "The traveler’s sibling", 2);
        addQuestion(q28);
        Question q29 = new Question("Who created the Jinn?", 0, "King Deshret", "Greater Lord Rukkhadevata", "Goddess of Flowers", "Lesser Lord Kusanali", 3);
        addQuestion(q29);
        Question q30 = new Question("Which of the following characters has Chiori designed clothing for?", 0, "Kirara", "Furina", "Rosaria", "Yelan", 1);
        addQuestion(q30);
        Question q31 = new Question("Hu Tao is the ___ Direction of the Wangsheng Funeral Parlor.", 0, "1st", "55th", "99th", "77th", 4);
        addQuestion(q31);
        Question q32 = new Question("What is Mika’s title in the Knights of Favonius?", 0, "Front-line surveyor", "Captain of the Reconnaissance Company", "Coordinator of the Knights of Favonius", "Captain of the Investigation Team", 1);
        addQuestion(q32);
        Question q33 = new Question("Which of the following nations does not have a unique Seelie?", 0, "Liyue", "Inazuma", "Mondstadt", "Sumeru", 3);
        addQuestion(q33);
        Question q34 = new Question("Which building project caused Kaveh to become heavily in debt?", 0, "House of Daena", "Palace of Alcazarzaray", "Zubayr Theater", "Wikala Funduq", 2);
        addQuestion(q34);
        Question q35 = new Question("Rhinedottir is responsible for creating a creature known as \"Cretaceus\". What other name is this creature known by?", 0, "Durin", "Riftwolf", "Elynas", "Albedo", 4);
        addQuestion(q35);
        Question q36 = new Question("Which group is Klee’s mother, Alice, affiliated with?", 0, "Hexenzirkel", "Verurteilung", "Narzissenkreuz Ordo", "Ipsissimus", 1);
        addQuestion(q36);
        Question q37 = new Question("Which two characters list Charcoal-Baked Ajilenakh Cakes as their least favorite food?", 0, "Faruzan and Wanderer", "Alhaitham and Gaming", "Kaveh and Lyney", "Dori and Neuvillette", 4);
        addQuestion(q37);
        Question q38 = new Question("What is the name of the construct that Lyney summons with his skill?", 0, "Grim-Malkin Cat", "Grin-Malkin Hat", "Grim-Marking Hat", "Grin-Marking Cat", 2);
        addQuestion(q38);
        Question q39 = new Question("How did \"Father\" come to possess her position as a Harbinger and the title of \"Arlecchino\"?", 0, "She killed the previous Arlecchino and took over the orphanage", "She was granted the position by the Tsaritsa after the previous Arlecchino passed away", "She purchased the orphanage from the previous owner", "She framed the previous Arlecchino for murder and took over her place as the leader", 1);
        addQuestion(q39);
        Question q40 = new Question("Which character began to harbor ill feelings toward Heizou after he abandoned their partnership?", 0, "Takashi", "Hajime", "Sango", "Ryuuji", 3);
        addQuestion(q40);
        Question q41 = new Question("What did Thoma bring to Inazuma as a gift for his father?", 0, "Dandelion Wine", "Apple Cider", "Fruits of the Festival", "Wolfhook Juice", 1);
        addQuestion(q41);
        Question q42 = new Question("WWhat is the name of the spirit that possesses Cyno's body?", 0, "King Deshret", "Hermanubis", "Ormazd", "Parvezravan", 2);
        addQuestion(q42);

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
