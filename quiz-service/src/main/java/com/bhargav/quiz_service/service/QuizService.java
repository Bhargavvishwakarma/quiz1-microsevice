package com.bhargav.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bhargav.quiz_service.Model.QuestionWrapper;
import com.bhargav.quiz_service.Model.Quiz;
import com.bhargav.quiz_service.Model.Response;
import com.bhargav.quiz_service.dao.QuizDao;
import com.bhargav.quiz_service.feign.QuizInterface;


@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;
     
    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Sucess",HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.getById(id);
        List<Integer> questionIds = quiz.getQuestionsIds();
        List<QuestionWrapper> questions = quizInterface.getQuestionsFromId(questionIds).getBody();   
        return new ResponseEntity<>(questions,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResults(Integer id, List<Response> responces) {
        Integer right = quizInterface.getScore(responces).getBody(); 
        return new ResponseEntity<>(right,HttpStatus.OK);
    }


}
