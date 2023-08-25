package Service.Impl;

import DataObjects.Level;
import DataObjects.Question;
import DataStore.Impl.QuestionDataStoreImpl;
import DataStore.QuestionDataStore;
import Service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private static final QuestionDataStore questionDataStore = new QuestionDataStoreImpl();

    @Override
    public void createQuestion(Level level, int score) {
        Question question = new Question();
        question.setQuestionLevel(level);
        question.setScore(score);

        questionDataStore.addQuestion(question);
        System.out.println("Question with difficulty level: " + level + " added to database with ID: " + question.getQuestionId());
    }

    @Override
    public void listQuestion(Level level) {
        List<Question> questions = questionDataStore.getQuestionList(level);

        questions.forEach(question -> System.out.println(question.getQuestionId() + " " + question.getScore() + " "
                + question.getQuestionLevel()));
    }
}
