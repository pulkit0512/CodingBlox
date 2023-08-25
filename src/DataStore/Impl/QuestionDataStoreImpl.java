package DataStore.Impl;

import DataObjects.Level;
import DataObjects.Question;
import DataStore.QuestionDataStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDataStoreImpl implements QuestionDataStore {
    private static final Map<String, List<Question>> questionDB = new HashMap<>();
    @Override
    public void addQuestion(Question question) {
        String level = question.getQuestionLevel().toString();
        questionDB.putIfAbsent(level, new ArrayList<>());
        questionDB.get(level).add(question);
    }

    @Override
    public List<Question> getQuestionList(Level level) {
        if(level.equals(Level.ALL)) {
            List<Question> questions = new ArrayList<>();
            questions.addAll(questionDB.get(Level.LOW.toString()));
            questions.addAll(questionDB.get(Level.MEDIUM.toString()));
            questions.addAll(questionDB.get(Level.HIGH.toString()));

            return questions;
        } else {
            return questionDB.get(level.toString());
        }
    }
}
