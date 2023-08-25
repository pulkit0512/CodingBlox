package DataObjects;

public class User {
    private static int uniqueId = 0;
    private String userId;
    private String name;
    private int score;

    public User() {
        uniqueId++;
        this.userId = "User" + uniqueId + "_";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
