package com.bhargav.question_serivice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bhargav.question_serivice.Model.Question;
import com.bhargav.question_serivice.Model.QuestionWrapper;
import com.bhargav.question_serivice.Model.Response;
import com.bhargav.question_serivice.dao.QuestionDao;


@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
             return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        } catch (Exception e) {
             return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
       
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
       try {
         return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
       } catch (Exception e) {
         return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
       }
    }

    public ResponseEntity<String> addQuestion(Question question) {
       
        try {
             questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception while adding question",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQ) {
      List<Integer> questions = questionDao.findRandomQuestionIdsByCategory(category, numQ);
      return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsId) {
        List<QuestionWrapper> questionsWrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for(Integer id: questionsId){
          questions.add(questionDao.getById(id));
        }

        for(Question q: questions){
          QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getCategory(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getQuestionTitle());
          questionsWrapper.add(questionWrapper);
        }

        return new ResponseEntity<>(questionsWrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
      int right = 0;
      for(Response response:responses){
        if(response.getResponse().equals(questionDao.getById(response.getId()).getRightAnswer())){
          right++;
        }
      }
      return new ResponseEntity<>(right,HttpStatus.OK);
    }
    
}
