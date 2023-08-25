package DataObjects;

import java.util.List;

public class Performance {
    private int score;
    private List<Integer> solvedQuestions;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Integer> getSolvedQuestions() {
        return solvedQuestions;
    }

    public void setSolvedQuestions(List<Integer> solvedQuestions) {
        this.solvedQuestions = solvedQuestions;
    }
}
