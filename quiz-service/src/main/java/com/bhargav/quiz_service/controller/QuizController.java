package com.bhargav.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhargav.quiz_service.Model.QuestionWrapper;
import com.bhargav.quiz_service.Model.QuizDto;
import com.bhargav.quiz_service.Model.Response;
import com.bhargav.quiz_service.service.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumQuestions(),quizDto.getTitle());
    } 

    @GetMapping("quizget/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responces) {
       
        return quizService.calculateResults(id,responces);
    }
}
