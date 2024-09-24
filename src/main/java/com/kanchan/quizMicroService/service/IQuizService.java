package com.kanchan.quizMicroService.service;

import java.util.List;

import com.kanchan.quizMicroService.to.QuestionTO;
import com.kanchan.quizMicroService.to.SubmitResponseTO;

public interface IQuizService {

	public String buildQuiz(String category, Integer noOfQuestions, String title);

	public List<QuestionTO> fetchQuizById(Integer id);

	public Integer fetchResult(Integer id, List<SubmitResponseTO> requestTO);

}
