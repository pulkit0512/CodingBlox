package DataObjects;

import java.util.Map;

public class Contest {
    private static int uniqueId = 0;
    private final int contestId;
    private String contestName;
    private Level contestLevel;
    private String creator;
    private Map<String, Performance> contestPerformance;

    public Contest() {
        uniqueId++;
        this.contestId = uniqueId;
    }

    public int getContestId() {
        return contestId;
    }

    public Level getContestLevel() {
        return contestLevel;
    }

    public void setContestLevel(Level contestLevel) {
        this.contestLevel = contestLevel;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Map<String, Performance> getContestPerformance() {
        return contestPerformance;
    }

    public void setContestPerformance(Map<String, Performance> contestPerformance) {
        this.contestPerformance = contestPerformance;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }
}
