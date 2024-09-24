package com.kanchan.quizMicroService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanchan.quizMicroService.service.IQuizService;
import com.kanchan.quizMicroService.to.QuestionTO;
import com.kanchan.quizMicroService.to.QuizTO;
import com.kanchan.quizMicroService.to.SubmitResponseTO;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	IQuizService quizService;

	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizTO quizReqTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				quizService.buildQuiz(quizReqTO.getCategory(), quizReqTO.getNumOfQuestion(), quizReqTO.getTitle()));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionTO>> getQuizById(@PathVariable Integer id) {
		return ResponseEntity.ok(quizService.fetchQuizById(id));
	}

	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitTest(@PathVariable Integer id,
			@RequestBody List<SubmitResponseTO> responseTO) {
		return ResponseEntity.ok(quizService.fetchResult(id, responseTO));
	}
}
