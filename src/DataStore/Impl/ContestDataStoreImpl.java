package DataStore.Impl;

import DataObjects.Contest;
import DataObjects.Level;
import DataStore.ContestDataStore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContestDataStoreImpl implements ContestDataStore {
    private static final Map<Integer, Contest> contestDB = new HashMap<>();
    private static final Map<String, Set<Contest>> contestLevelDB = new HashMap<>();

    @Override
    public void addContest(Contest contest) {
        contestDB.put(contest.getContestId(), contest);

        String level = contest.getContestLevel().toString();
        contestLevelDB.putIfAbsent(level, new HashSet<>());
        contestLevelDB.get(level).add(contest);
    }

    @Override
    public Contest getContest(int contestId) {
        return contestDB.get(contestId);
    }

    @Override
    public void updateContest(Contest contest) {
        Contest previousContest = contestDB.get(contest.getContestId());
        contestDB.put(contest.getContestId(), contest);

        String level = contest.getContestLevel().toString();
        contestLevelDB.get(level).remove(previousContest);
        contestLevelDB.get(level).add(contest);
    }

    @Override
    public Set<Contest> getContestList(Level level) {
        if(level.equals(Level.ALL)) {
            Set<Contest> contests = new HashSet<>();
            contests.addAll(contestLevelDB.get(Level.LOW.toString()));
            contests.addAll(contestLevelDB.get(Level.MEDIUM.toString()));
            contests.addAll(contestLevelDB.get(Level.HIGH.toString()));

            return contests;
        } else {
            return contestLevelDB.get(level.toString());
        }
    }
}
