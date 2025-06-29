package com.bhargav.question_serivice.dao;

import org.springframework.stereotype.Repository;

import com.bhargav.question_serivice.Model.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	List<Question> findByCategory(String category);
    
    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RANDOM()", nativeQuery = true)
    List<Integer> findRandomQuestionIdsByCategory(@Param("category") String category, @Param("numQ") Integer numQ);
}
