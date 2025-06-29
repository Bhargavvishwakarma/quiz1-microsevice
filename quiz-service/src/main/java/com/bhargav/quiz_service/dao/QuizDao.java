package com.bhargav.quiz_service.dao;

import org.springframework.stereotype.Repository;

import com.bhargav.quiz_service.Model.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
}