package com.kanchan.quizMicroService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kanchan.quizMicroService.to.QuestionTO;
import com.kanchan.quizMicroService.to.SubmitResponseTO;

@FeignClient("QUESTIONSERVICE")
public interface IQuizFeignClient {
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> generateQuestionForQuiz(@RequestParam String category, @RequestParam Integer numOfQuestion);

	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionTO>> getQuestionsByIds(@RequestBody List<Integer> idList);

	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<SubmitResponseTO> quizSubmission);
}
