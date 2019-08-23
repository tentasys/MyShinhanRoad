package com.example.shinple.VO;


public class QuizVO {
    String question;
    String example;

    public String getquestion() {
        return question;
    }

    public void setquestion(String question) {
        this.question= question;
    }

    public String getexample() {
        return example;
    }

    public void setexample(String example) {
        this.example= example;
    }


    public QuizVO(String question, String example) {
        this.question = question;
        this.example = example;
    }
}
