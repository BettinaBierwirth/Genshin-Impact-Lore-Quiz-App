package com.bettinabierwirth.genshinimpactlorequiz;

/**
 * Represents a question in the Genshin Impact Lore Quiz application.
 * This class serves as a bridge to the SQL database, providing getters and setters for question attributes.
 */
public class Question {
    private String question;
    private int imageReference;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private int answerNr;

    /**
     * Default constructor for the Question class.
     * Creates an instance of the class with default values.
     */
    public Question() {
    }

    /**
     * Parameterized constructor for the Question class.
     * Creates an instance of the class with specified attributes.
     *
     * @param question      The text of the question.
     * @param imageReference The resource ID of the image associated with the question.
     * @param option1       The first option for the question.
     * @param option2       The second option for the question.
     * @param option3       The third option for the question.
     * @param option4       The fourth option for the question.
     * @param answerNr      The number corresponding to the correct answer option.
     */

    public Question(String question, int imageReference, String option1, String option2, String option3, String option4, int answerNr) {
        this.question = question;
        this.imageReference = imageReference;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
    }

    /**
     * Getter and Setter for the Questions and answers.
     *
     * @return The text of the questions and answers.
     */

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getImageReference() {
        return imageReference;
    }

    public void setImageReference(int imageReference) {
        this.imageReference = imageReference;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
}
