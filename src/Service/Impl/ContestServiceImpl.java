package Service.Impl;

import DataObjects.*;
import DataStore.ContestDataStore;
import DataStore.Impl.ContestDataStoreImpl;
import DataStore.Impl.QuestionDataStoreImpl;
import DataStore.Impl.UserDataStoreImpl;
import DataStore.QuestionDataStore;
import DataStore.UserDataStore;
import Service.ContestService;

import java.util.*;

public class ContestServiceImpl implements ContestService {
    private static final ContestDataStore contestDataStore = new ContestDataStoreImpl();
    private static final UserDataStore userDataStore = new UserDataStoreImpl();
    private static final QuestionDataStore questionDataStore = new QuestionDataStoreImpl();
    private static final Random random = new Random();

    @Override
    public void createContest(String contestName, Level contestLevel, String creatorUser) {
        Contest contest = new Contest();
        contest.setContestLevel(contestLevel);
        contest.setContestName(contestName);
        contest.setContestPerformance(new HashMap<>());
        contest.setCreator(creatorUser);
        contest.getContestPerformance().put(creatorUser, new Performance());

        contestDataStore.addContest(contest);
    }

    @Override
    public void listContest(Level level) {
        Set<Contest> contestSet = contestDataStore.getContestList(level);

        contestSet.forEach(contest -> System.out.println(contest.getContestId() + " " + contest.getContestLevel() + " "
                + contest.getContestName() + " " + contest.getCreator()));
    }

    @Override
    public void attendContest(int contestId, String userName) {
        Contest contest = contestDataStore.getContest(contestId);
        if (contest.getContestPerformance().containsKey(userName)) {
            System.out.println("User already participating in the contest");
            return;
        }
        contest.getContestPerformance().put(userName, new Performance());
        contestDataStore.updateContest(contest);
        System.out.println("User added to the contest.");
    }

    @Override
    public void runContest(int contestId, String creatorUser) {
        Contest contest = contestDataStore.getContest(contestId);
        if (!contest.getCreator().equalsIgnoreCase(creatorUser)) {
            System.out.println("Creator user and user trying to run the contest are not same.");
            return;
        }

        Set<String> users = contest.getContestPerformance().keySet();
        List<Question> questions = questionDataStore.getQuestionList(contest.getContestLevel());

        for(String user : users) {
            int question = random.nextInt(6);
            List<Integer> solved = new ArrayList<>();
            int score = 0;
            for(int i=1;i<=question;i++) {
                int questionNumber = random.nextInt(questions.size());
                score += questions.get(questionNumber).getScore();
                solved.add(questions.get(questionNumber).getQuestionId());
            }

            contest.getContestPerformance().get(user).setScore(score);
            contest.getContestPerformance().get(user).setSolvedQuestions(solved);
        }

        printContestSummary(contest);

        updateUserRatings(contest);

        contestDataStore.updateContest(contest);
    }

    @Override
    public void contestHistory(int contestId) {
        Contest contest = contestDataStore.getContest(contestId);

        printContestSummary(contest);
    }

    private void printContestSummary(Contest contest) {
        Map<String, Performance> contestPerformance = contest.getContestPerformance();
        contestPerformance.forEach((user, performance) -> System.out.println(user + " " + performance.getScore() + " [" + performance.getSolvedQuestions() + "]"));
    }

    private void updateUserRatings(Contest contest) {
        Map<String, Performance> contestPerformance = contest.getContestPerformance();
        Level contestLevel = contest.getContestLevel();

        int contestPenaltyScore = 0;
        if (contestLevel.equals(Level.LOW)) {
            contestPenaltyScore = 50;
        } else if (contestLevel.equals(Level.MEDIUM)) {
            contestPenaltyScore = 30;
        }

        for(Map.Entry<String, Performance> entry : contestPerformance.entrySet()) {
            String userName = entry.getKey();
            User user = userDataStore.getUser(userName);
            int currentScore = user.getScore();
            int newScore = currentScore + (entry.getValue().getScore() - contestPenaltyScore);
            user.setScore(newScore);
            userDataStore.updateUser(user);
        }
    }
}
