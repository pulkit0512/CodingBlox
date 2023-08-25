package DataObjects;

public class Question {
    private static int uniqueId = 0;
    private final int questionId;
    private Level questionLevel;
    private int score;

    public Question() {
        uniqueId++;
        this.questionId = uniqueId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public Level getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(Level questionLevel) {
        this.questionLevel = questionLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
