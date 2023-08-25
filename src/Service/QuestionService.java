package Service;

import DataObjects.Level;

public interface QuestionService {
    void createQuestion(Level level, int score);
    void listQuestion(Level level);
}
