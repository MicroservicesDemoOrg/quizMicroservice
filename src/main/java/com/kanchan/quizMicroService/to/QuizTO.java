package com.kanchan.quizMicroService.to;

public class QuizTO {
    private String category;
    private  String title;
    private Integer numOfQuestion;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(Integer numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public QuizTO(String category, String title, Integer numOfQuestion) {
        this.category = category;
        this.title = title;
        this.numOfQuestion = numOfQuestion;
    }

    public QuizTO() {
    }

}
