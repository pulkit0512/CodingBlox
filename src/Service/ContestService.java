package Service;

import DataObjects.Level;

public interface ContestService {
    void createContest(String contestName, Level contestLevel, String creatorUser);
    void listContest(Level level);
    void attendContest(int contestId, String userName);
    void runContest(int contestId, String creatorUser);
    void contestHistory(int contestId);
}
