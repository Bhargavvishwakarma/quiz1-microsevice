package com.bhargav.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.bhargav.quiz_service.Model.QuestionWrapper;
import com.bhargav.quiz_service.Model.Response;

@FeignClient("QUESTION-SERIVICE")
public interface QuizInterface {

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ);

    @GetMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsId);
    
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
} 