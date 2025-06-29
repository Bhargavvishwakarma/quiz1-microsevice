package com.bhargav.question_serivice.controller;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhargav.question_serivice.service.QuestionService;
// Update the import below to match the actual package of the Question class
import com.bhargav.question_serivice.Model.Question;
import com.bhargav.question_serivice.Model.QuestionWrapper;
import com.bhargav.question_serivice.Model.Response;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("question")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;

    @Autowired
    private Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        String port = environment.getProperty("local.server.port", "port not found");
        System.out.println("Running on port: " + port);
        return questionService.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ) {
        return questionService.getQuestionsForQuiz(category,numQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsId){
        String port = environment.getProperty("local.server.port", "port not found");
        System.out.println("Running on port: " + port);
        return questionService.getQuestionsFromId(questionsId);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.calculateScore(responses);
    }
}
