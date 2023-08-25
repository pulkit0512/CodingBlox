package DataStore;

import DataObjects.Contest;
import DataObjects.Level;

import java.util.Set;

public interface ContestDataStore {
    void addContest(Contest contest);
    Contest getContest(int contestId);
    void updateContest(Contest contest);
    Set<Contest> getContestList(Level level);
}
