package com.kanchan.quizMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kanchan.quizMicroService.models.Quiz;

public interface IQuizRepository extends JpaRepository<Quiz, Integer> {

}
