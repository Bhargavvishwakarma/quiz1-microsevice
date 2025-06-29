package com.bhargav.quiz_service.Model;

import lombok.Data;

@Data
public class QuizDto {
    String category;
    Integer numQuestions;
    String title;
}
