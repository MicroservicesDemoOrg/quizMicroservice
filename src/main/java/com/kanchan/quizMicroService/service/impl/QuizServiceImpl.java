package com.kanchan.quizMicroService.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.kanchan.quizMicroService.IQuizFeignClient;
import com.kanchan.quizMicroService.models.Quiz;
import com.kanchan.quizMicroService.repository.IQuizRepository;
import com.kanchan.quizMicroService.service.IQuizService;
import com.kanchan.quizMicroService.to.QuestionTO;
import com.kanchan.quizMicroService.to.SubmitResponseTO;

@Service
public class QuizServiceImpl implements IQuizService {

	@Autowired
	IQuizRepository quizRepository;

	@Autowired
	IQuizFeignClient quizFeignClient;

	@Override
	public String buildQuiz(String category, Integer noOfQuestions, String title) {

		List<Integer> questionList = quizFeignClient.generateQuestionForQuiz(category, noOfQuestions).getBody();
		//questionRepository.findRandomQuestionsByCategory(category, noOfQuestions);

		Quiz quiz = new Quiz();

		quiz.setTitle(title);
		quiz.setQuestionIds(questionList);

		quizRepository.save(quiz);
		return "success";
	}

	@Override
	public List<QuestionTO> fetchQuizById(Integer id) {
		Optional<Quiz> quiz = quizRepository.findById(id);
		
		if(!quiz.isPresent())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No Quiz found.");
		
		ResponseEntity<List<QuestionTO>> questionListResp = quizFeignClient.getQuestionsByIds(quiz.get().getQuestionIds()); // quiz.get().getQuestions();
		
		return questionListResp.getBody();
		
		/*
		 * return questionList.stream() .map(question -> new QuestionTO(
		 * question.getId(), question.getQuestionTitle(), question.getOption1(),
		 * question.getOption2(), question.getOption3(), question.getOption4() ) )
		 * .collect(Collectors.toList());
		 */
	}

	@Override
	public Integer fetchResult(Integer id, List<SubmitResponseTO> responseList) {
		
		Optional<Quiz> quiz = quizRepository.findById(id);
		
		if(!quiz.isPresent())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No quiz found by id: " + id);
		
		//List<Question> questionList = null;//quiz.get().getQuestions();

		/*
		 * int i = 0; int result = 0; for (SubmitResponseTO response : responseList) {
		 * if
		 * (response.getResponse().equalsIgnoreCase(questionList.get(i).getRightAnswer()
		 * )) result++; i++; }
		 */
		 
		
		/*
		 * return (int) IntStream.range(0, responseList.size()) .filter(i ->
		 * responseList.get(i).getResponse().equalsIgnoreCase(questionList.get(i).
		 * getRightAnswer())) .count();
		 */
		
			ResponseEntity<Integer> result = quizFeignClient.getScore(responseList);
			
			return result.getBody();

	}

}
