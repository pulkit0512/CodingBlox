package DataStore;

import DataObjects.Level;
import DataObjects.Question;

import java.util.List;

public interface QuestionDataStore {
    void addQuestion(Question question);
    List<Question> getQuestionList(Level level);
}
