import DataObjects.Level;
import Service.ContestService;
import Service.Impl.ContestServiceImpl;
import Service.Impl.QuestionServiceImpl;
import Service.Impl.UserServiceImpl;
import Service.QuestionService;
import Service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final File FILE = new File("/Users/pulkitarora/learning/CodingBlox/input.txt");
    private static final UserService userService = new UserServiceImpl();
    private static final QuestionService questionService = new QuestionServiceImpl();
    private static final ContestService contestService = new ContestServiceImpl();
    private static final Scanner sc;

    static {
        try {
            sc = new Scanner(FILE);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Coding Blox");

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println("===" + input + "===");
            String[] params = input.split(" ");

            if(params[0].equalsIgnoreCase("CreateUser")) {
                String name = params[1];
                userService.createUser(name);
            } else if(params[0].equalsIgnoreCase("CreateQuestion")) {
                Level questionLevel = Level.valueOf(params[1]);
                int score = Integer.parseInt(params[2]);
                questionService.createQuestion(questionLevel, score);
            } else if(params[0].equalsIgnoreCase("ListQuestion")) {
                if (params.length==1) {
                    questionService.listQuestion(Level.ALL);
                } else {
                    Level questionLevel = Level.valueOf(params[1]);
                    questionService.listQuestion(questionLevel);
                }
            } else if(params[0].equalsIgnoreCase("CreateContest")) {
                String contestName = params[1];
                Level contestLevel = Level.valueOf(params[2]);
                String creatorUserName = params[3];
                contestService.createContest(contestName, contestLevel, creatorUserName);
            } else if(params[0].equalsIgnoreCase("ListContest")) {
                if (params.length==1) {
                    contestService.listContest(Level.ALL);
                } else {
                    Level contestLevel = Level.valueOf(params[1]);
                    contestService.listContest(contestLevel);
                }
            } else if(params[0].equalsIgnoreCase("AttendContest")) {
                int contestId = Integer.parseInt(params[1]);
                String userName = params[2];
                contestService.attendContest(contestId, userName);
            } else if(params[0].equalsIgnoreCase("RunContest")) {
                int contestId = Integer.parseInt(params[1]);
                String creatorUserName = params[2];
                contestService.runContest(contestId, creatorUserName);
            } else if(params[0].equalsIgnoreCase("LeaderBoard")) {
                String order = params[1];
                userService.getLeaderBoard(order);
            } else if(params[0].equalsIgnoreCase("ContestHistory")) {
                int contestId = Integer.parseInt(params[1]);
                contestService.contestHistory(contestId);
            }
        }
    }
}